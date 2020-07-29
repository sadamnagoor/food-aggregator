package com.aggregator.food

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodAggregatorApplication

fun main(args: Array<String>) {
	runApplication<FoodAggregatorApplication>(*args)
}
