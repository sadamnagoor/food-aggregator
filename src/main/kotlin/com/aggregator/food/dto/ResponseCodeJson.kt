package com.aggregator.food.dto

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by Nagoor on 28-July-2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseCodeJson(
        var errorCode: Int,
        var message: String)