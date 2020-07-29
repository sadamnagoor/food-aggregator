package com.aggregator.food.exception

/**
 * Created by Nagoor on 28-July-2020.
 */
class ExceptionThrower {
    @Throws(Exception::class)
    fun throwGeneralException() {
        val e = Exception("Error from General Exception")
        throw e
    }

    @Throws(CustomException::class)
    fun throwCustomException(errorcode: Int, message: String?) {
        val e = CustomException()
        e.code = errorcode
        e.message = message!!
        throw e
    }
}