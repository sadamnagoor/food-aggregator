package com.aggregator.food.dto

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by Nagoor on 28-July-2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class UniversalResponse<T> {
        lateinit var responseCodeJson: ResponseCodeJson
        lateinit var list: List<*>
        var `object`: T? = null
    }