package com.quizapp.backendservice.domain.dto.response

data class ResGetQuestionDto(
    var id: String? = null,
    var title: String? = null,
    var optionA: String? = null,
    var optionB: String? = null,
    var optionC: String? = null,
    var optionD: String? = null,
    var correctAnswer: String? = null,
)
