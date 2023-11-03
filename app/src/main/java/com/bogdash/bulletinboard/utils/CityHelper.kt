package com.bogdash.bulletinboard.utils

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.util.Locale

object CityHelper {
    fun getAllCountries(context: Context): ArrayList<String> {
        var tempArray = ArrayList<String>()
        try {
            val inputStream: InputStream = context.assets.open("countriesToCities.json")
            val size: Int = inputStream.available()
            val bytesArray = ByteArray(size)
            inputStream.read(bytesArray)
            val jsonFile = String(bytesArray)
            val jsonObject = JSONObject(jsonFile)
            val countriesNames = jsonObject.names()

            if (countriesNames != null) {
                for (item in 0 until countriesNames.length()) {
                    tempArray.add(countriesNames.getString(item))
                }
            }

        } catch (e: IOException) {

        }

        return tempArray
    }

    fun filterListData(list: ArrayList<String>, searchText: String?) : ArrayList<String> {
        val tempList = ArrayList<String>()
        tempList.clear()

        if (searchText == null) {
            tempList.add("No result")
            return tempList
        }
        list.forEach { selection ->
            if (selection.lowercase(Locale.ROOT).startsWith(searchText.lowercase(Locale.ROOT)))
                tempList.add(selection)
        }
        if (tempList.size == 0) tempList.add("No result")
        return tempList
    }
}