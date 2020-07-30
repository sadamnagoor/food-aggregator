package com.aggregator.food.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by Nagoor on 28-July-2020.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class FoodReceiverDto {
    var id: String? = null
    var name: String? = null
    var quantity = 0
    var price: String? = null
    var category: String? = null
    var itemId: String? = null
    var itemName: String? = null
    var productId: String? = null
    var productName: String? = null
    override fun toString(): String {
        return "FoodReceiverDto(id=$id, name=$name, quantity=$quantity, price=$price, category=$category, itemId=$itemId, itemName=$itemName, productId=$productId, productName=$productName)"
    }
}