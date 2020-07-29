package com.aggregator.food.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by Nagoor on 28-July-2020.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class FoodReceiverDto {
    lateinit var id: String
    lateinit var name: String
    lateinit var quantity: String
    lateinit var price: String
    lateinit var category: String
}