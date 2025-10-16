package com.quizapp.backendservice.domain.dto.response

data class ResDetailQuestionDto(
    var id: String? = null,
    var title: String? = null,
    var optionA: String? = null,
    var optionB: String? = null,
    var optionC: String? = null,
    var optionD: String? = null,
    var correctAnswer: String? = null,
    var isActive: Boolean? = null,
    var isDelete: Boolean? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null
)
