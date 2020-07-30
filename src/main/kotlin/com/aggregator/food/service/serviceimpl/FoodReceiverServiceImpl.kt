package com.aggregator.food.service.serviceimpl

import com.aggregator.food.constants.Constants
import com.aggregator.food.dto.FoodReceiverDto
import com.aggregator.food.dto.ResponseCodeJson
import com.aggregator.food.dto.UniversalResponse
import com.aggregator.food.service.FoodReceiverService
import com.aggregator.food.utils.FoodSuppliersUtil.Companion.getItems
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import java.util.function.Consumer

/**
 * Created by Nagoor on 29-07-2020
 */
@Service
class FoodReceiverServiceImpl : FoodReceiverService, Constants {
    private val list = ArrayList<FoodReceiverDto>();

    /**
     * function describes about getting
     * item based on item name and category like
     * fruits or vegetable or grains
     */
    @Throws(Exception::class)
    override fun buyItemBasedOnItemName(itemName: String, category: String): UniversalResponse<FoodReceiverDto> {
        val universalResponse = UniversalResponse<FoodReceiverDto>()
        val responseStr = getItems(category)
        val foodReceiverDtoList = convertJsonToList(responseStr)
        val foodReceiverDto = findItem(foodReceiverDtoList, itemName)
        return if (foodReceiverDto.name != null) {
            universalResponse.`object` = foodReceiverDto
            universalResponse.responseCodeJson = ResponseCodeJson(200, "success")
            universalResponse
        } else {
            universalResponse.responseCodeJson = ResponseCodeJson(404, "item not found")
            universalResponse
        }
    }

    /**
     * function describes about getting
     * item based on item name , quantity , price and category like
     * fruits or vegetable or grains
     */
    @Throws(Exception::class)
    override fun buyItemBasedOnItemDetails(foodReceiverDto: FoodReceiverDto): UniversalResponse<FoodReceiverDto> {
        val universalResponse = UniversalResponse<FoodReceiverDto>()
        var responseStr = ""
        if (list.isEmpty()) {
            responseStr = getItems(foodReceiverDto.category)
            val foodReceiverDtoList = convertJsonToList(responseStr)
            list.addAll(foodReceiverDtoList)
        }
        val foodReceiverDto1 = compareItemDetails(list, foodReceiverDto)
        return if (foodReceiverDto1.name != null) {
            universalResponse.`object` = foodReceiverDto1
            universalResponse.responseCodeJson = ResponseCodeJson(200, "success")
            universalResponse
        } else {
            universalResponse.responseCodeJson = ResponseCodeJson(402, "item not found")
            universalResponse
        }
    }

    /**
     * function describes about making
     * calls parallel to the suppliers
     * based on categories getting data and
     * comparing on item name
     */
    @Throws(Exception::class)
    override fun fastBuyItem(foodReceiverDto: FoodReceiverDto): UniversalResponse<List<FoodReceiverDto>> {
        val universalResponse = UniversalResponse<List<FoodReceiverDto>>()
        val itemsList: MutableList<FoodReceiverDto> = ArrayList()
        val finalItems: List<FoodReceiverDto?>
        var foodReceiverDtoList: List<FoodReceiverDto>
        val categoryArray = foodReceiverDto.category?.split(",".toRegex())?.toTypedArray()
        if (categoryArray != null) {
            for (category in categoryArray) {
                val responseStr = getItems(category)
                foodReceiverDtoList = convertJsonToList(responseStr)
                if (!foodReceiverDtoList.isEmpty()) {
                    itemsList.addAll(foodReceiverDtoList)
                }
            }
        }
        return if (itemsList.isNotEmpty()) {
            finalItems = findItems(itemsList, foodReceiverDto.name)
            universalResponse.list = finalItems
            universalResponse.responseCodeJson = ResponseCodeJson(200, "success")
            universalResponse
        } else {
            universalResponse.responseCodeJson = ResponseCodeJson(403, "item not found")
            universalResponse
        }
    }

    /**
     * function used to convert
     * the json to list
     */
    @Throws(Exception::class)
    private fun convertJsonToList(responseStr: String): List<FoodReceiverDto> {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(responseStr,
                objectMapper.typeFactory.constructCollectionType(MutableList::class.java, FoodReceiverDto::class.java))
    }

    /**
     * function describes about comparing
     * the item details
     */
    private fun compareItemDetails(foodReceiverDtoList: List<FoodReceiverDto>, foodReceiverDto: FoodReceiverDto): FoodReceiverDto {
        val atomicReference = AtomicReference(FoodReceiverDto())
        foodReceiverDtoList.forEach(Consumer { foodReceiverDto1: FoodReceiverDto -> if (foodReceiverDto1.name == foodReceiverDto.name && foodReceiverDto1.quantity == foodReceiverDto.quantity && foodReceiverDto1.price == foodReceiverDto.price) atomicReference.set(foodReceiverDto1) })
        return atomicReference.get()
    }

    /**
     * function describes about finding
     * item based on item name
     */
    private fun findItem(foodReceiverDtoList: List<FoodReceiverDto>, itemName: String): FoodReceiverDto {
        val foodReceiverDto = AtomicReference(FoodReceiverDto())
        foodReceiverDtoList.forEach(Consumer { foodReceiverDto1: FoodReceiverDto -> if (foodReceiverDto1.name == itemName) foodReceiverDto.set(foodReceiverDto1) })
        return foodReceiverDto.get()
    }

    /**
     * function describes about finding
     * item based on item names
     */
    private fun findItems(foodReceiverDtoList: List<FoodReceiverDto>, itemName: String?): List<FoodReceiverDto?> {
        val itemsList: MutableList<FoodReceiverDto?> = ArrayList()
        val itemNamesArray = itemName?.split(",".toRegex())?.toTypedArray()
        for (foodReceiverDto in foodReceiverDtoList) {
            val result = itemNamesArray?.let { compareWithArray(foodReceiverDto, it) }
            if (result!!) {
                itemsList.add(foodReceiverDto)
            }
        }
        return itemsList
    }

    /**
     * function describes about comparing
     * the data in array
     */
    private fun compareWithArray(foodReceiverDto: FoodReceiverDto, itemArray: Array<String>): Boolean {
        var count = 0
        for (s in itemArray) {
            if (foodReceiverDto.name != null) {
                if (foodReceiverDto.name == s)
                    count++
            } else if (foodReceiverDto.itemName != null) {
                if (foodReceiverDto.itemName == s)
                    count++
            } else {
                if (foodReceiverDto.productName == s)
                    count++
            }
            if (count == 1)
                break
        }
        return count == 1
    }
}