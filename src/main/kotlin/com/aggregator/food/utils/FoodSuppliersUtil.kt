package com.aggregator.food.utils

import com.aggregator.food.constants.Constants
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/**
 * Created by Nagoor on 29-07-2020
 * util class is describes about
 * getting the data from the api end point
 * returning response json in string
 */
class FoodSuppliersUtil : Constants {
    companion object {
        @Throws(Exception::class)
        fun getItems(category: String?): String {
             var url = if (category.equals(Constants.FRUIT_CATEGORY, ignoreCase = true)) {
                Constants.FRUIT_API_ENDPOINT
            } else if (category.equals(Constants.GRAIN_CATEGORY, ignoreCase = true)) {
                Constants.GRAIN_API_ENDPOINT
            } else {
                Constants.VEGETABLE_API_ENDPOINT
            }
            val connection = URL(url).openConnection()
            connection.setRequestProperty("Accept", "application/json")
            val reader = BufferedReader(InputStreamReader(connection.getInputStream()))
            val results = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                results.append(line)
            }
            reader.close()
            return results.toString()
        }
    }
}