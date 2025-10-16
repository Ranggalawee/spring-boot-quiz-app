package com.quizapp.backendservice.exception

class CustomException(
    val exceptionMessage: String,
    val statusCode: Int,
    val data: Any? = null
): RuntimeException()