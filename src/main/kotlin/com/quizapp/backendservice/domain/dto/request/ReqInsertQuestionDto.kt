package com.quizapp.backendservice.domain.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ReqInsertQuestionDto(
    @field:NotBlank(message = "title cannot be blank")
    @field:NotNull(message = "title cannot null")
    val title: String,
    @field:NotBlank(message = "optionA cannot be blank")
    @field:NotNull(message = "optionA cannot null")
    val optionA: String,
    @field:NotBlank(message = "optionB cannot be blank")
    @field:NotNull(message = "optionB cannot null")
    val optionB: String,
    @field:NotBlank(message = "optionC cannot be blank")
    @field:NotNull(message = "optionC cannot null")
    val optionC: String,
    @field:NotBlank(message = "optionD cannot be blank")
    @field:NotNull(message = "optionD cannot null")
    val optionD: String,
    @field:NotBlank(message = "correctAnswer cannot be blank")
    @field:NotNull(message = "correctAnswer cannot null")
    val correctAnswer: String
)
