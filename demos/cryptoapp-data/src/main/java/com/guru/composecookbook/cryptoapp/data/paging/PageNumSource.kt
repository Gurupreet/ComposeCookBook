package com.guru.composecookbook.cryptoapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.EOFException
import java.io.IOException

class PageNumSource<Value : Any>(private val loadPage: suspend (pageNum: Int, pageSize: Int) -> List<Value>?) :
    PagingSource<Int, Value>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        return try {
            val page = params.key ?: 1
            //获取网络数据
            val result = loadPage(page, params.loadSize)
                ?: return LoadResult.Error(EOFException("No more data!"))

            LoadResult.Page(
                //需要加载的数据
                data = result,
                //如果可以往上加载更多就设置该参数，否则不设置
                prevKey = if (page == 1) null else page - 1,
                //加载下一页的key 如果传null就说明到底了
                nextKey = page.plus(1)
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}