package com.guru.composecookbook.moviesapp.data.db

import androidx.room.TypeConverter

class ListTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun gettingListFromString(genreIds: String?): List<Int>? {
            val list = arrayListOf<Int>()

            val array =
                genreIds?.split(",".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
            if (array.isNullOrEmpty()) {
                return null
            }
            for (s in array) {
                if (s.isNotEmpty()) {
                    list.add(Integer.parseInt(s))
                }
            }
            return list
        }

        @TypeConverter
        @JvmStatic
        fun writingStringFromList(list: List<Int>?): String? {
            var genreIds = ""
            if (genreIds.isEmpty()) {
                return null
            } else {
                for (i in list!!) {
                    genreIds += ",$i"
                }
            }

            return genreIds
        }
    }
}