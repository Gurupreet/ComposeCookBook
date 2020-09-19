package com.guru.composecookbook.ui.moviesappmvi.data.api

import com.guru.composecookbook.ui.moviesappmvi.data.api.apiresponses.MovieListResponse
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import kotlinx.coroutines.flow.Flow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing?")
    fun getMovies(@Query("page") page: Int ): Flow<Response<MovieListResponse>>

    @GET("movie/{id}?")
    fun getMovieDetail(@Path("id") movieId: String): Flow<Response<Movie>>

    @GET("movie/{movieId}/similar?")
    fun getSimilarMovies(@Path("movieId") movieId: String): Flow<Response<MovieListResponse>>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        operator fun invoke(): MovieApi {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request().url
                    .newBuilder()
                    .addQueryParameter("api_key", "BuildConfig.API_KEY")
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)
        }
    }
}