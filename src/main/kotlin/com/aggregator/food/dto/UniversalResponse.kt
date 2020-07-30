package com.aggregator.food.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by Nagoor on 28-July-2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class UniversalResponse<T> {
         var responseCodeJson: ResponseCodeJson? = null
         var list: List<*>? = null
         var `object`: T? = null
    }