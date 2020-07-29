package com.aggregator.food.controller

import com.aggregator.food.dto.FoodReceiverDto
import com.aggregator.food.dto.ResponseCodeJson
import com.aggregator.food.dto.UniversalResponse
import com.aggregator.food.exception.ExceptionThrower
import com.aggregator.food.service.FoodReceiverService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Created by Nagoor on 28-July-2020.
 * this controller describes handling
 * the api request made by receiver
 * for buy food.
 */
@RestController
@RequestMapping("/receiver")
class FoodReceiverController {
    @Autowired
    private lateinit var foodReceiverService: FoodReceiverService

    @GetMapping(value = ["/buy-item/{category}/{itemName}"])
    private fun buyItemBasedOnItemName(@PathVariable("category") category: String,
                                       @PathVariable("itemName") itemName: String): ResponseEntity<*> {
        val exceptionThrower = ExceptionThrower()
        val universalResponse: UniversalResponse<FoodReceiverDto>? = foodReceiverService.buyItemBasedOnItemName(itemName, category);
        val responseCodeJson: ResponseCodeJson? = universalResponse?.responseCodeJson
        if (responseCodeJson?.errorCode != 200) {
            responseCodeJson?.errorCode?.let { exceptionThrower.throwCustomException(it, responseCodeJson.message) }
        }
        return ResponseEntity<Any>(universalResponse, HttpStatus.OK)
    }

    @PostMapping(value = ["/buy-item-qty-price"])
    private fun buyItemBasedOnItemDetails(@RequestBody foodReceiverDto: FoodReceiverDto): ResponseEntity<*> {
        val exceptionThrower = ExceptionThrower()
        val universalResponse: UniversalResponse<FoodReceiverDto>? = foodReceiverService.buyItemBasedOnItemDetails(foodReceiverDto);
        val responseCodeJson: ResponseCodeJson? = universalResponse?.responseCodeJson
        if (responseCodeJson?.errorCode != 200) {
            responseCodeJson?.errorCode?.let { exceptionThrower.throwCustomException(it, responseCodeJson.message) }
        }
        return ResponseEntity<Any>(universalResponse, HttpStatus.OK)
    }

    @PostMapping(value = ["/fast-buy-item"])
    private fun fastBuyItem(@RequestBody foodReceiverDto: FoodReceiverDto): ResponseEntity<*> {
        val exceptionThrower = ExceptionThrower()
        val universalResponse: UniversalResponse<List<FoodReceiverDto>>? = foodReceiverService.fastBuyItem(foodReceiverDto);
        val responseCodeJson: ResponseCodeJson? = universalResponse?.responseCodeJson
        if (responseCodeJson?.errorCode != 200) {
            responseCodeJson?.errorCode?.let { exceptionThrower.throwCustomException(it, responseCodeJson.message) }
        }
        return ResponseEntity<Any>(universalResponse, HttpStatus.OK)
    }
}