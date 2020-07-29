package com.aggregator.food.exception

import org.springframework.stereotype.Component

/**
 * Created by Nagoor on 28-July-2020.
 *  class describes handling the runtime
 *  exception also overridden the message
 *  method to display our own messages
 */
@Component
class CustomException : RuntimeException() {
    var code = 0
    override lateinit var message: String

}