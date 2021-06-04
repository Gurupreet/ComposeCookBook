package com.guru.composecookbook.cryptoapp.data.db

import androidx.room.TypeConverter

class ListTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun gettingFloatListFromString(floatList: String?): List<Float>? {
            val list = arrayListOf<Float>()

            val array =
                floatList?.split(",".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
            if (array.isNullOrEmpty()) {
                return null
            }
            for (s in array) {
                if (s.isNotEmpty()) {
                    list.add(s.toFloat())
                }
            }
            return list
        }

        @TypeConverter
        @JvmStatic
        fun writingStringFromFloatList(list: List<Float>?): String? {
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