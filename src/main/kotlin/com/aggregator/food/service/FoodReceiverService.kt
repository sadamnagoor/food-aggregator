package com.aggregator.food.service

import com.aggregator.food.dto.FoodReceiverDto
import com.aggregator.food.dto.UniversalResponse
import java.util.*
import kotlin.Exception

/**
 * Created by Nagoor on 28-July-2020.
 */
interface FoodReceiverService {
    @Throws(Exception::class)
    fun buyItemBasedOnItemName(itemName: String, category: String): UniversalResponse<FoodReceiverDto>
    @Throws(Exception::class)
    fun buyItemBasedOnItemDetails(foodReceiverDto: FoodReceiverDto): UniversalResponse<FoodReceiverDto>
    @Throws(Exception::class)
    fun fastBuyItem(foodReceiverDto: FoodReceiverDto): UniversalResponse<List<FoodReceiverDto>>
}