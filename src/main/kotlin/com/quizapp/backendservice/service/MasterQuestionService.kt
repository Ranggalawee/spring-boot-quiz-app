package com.quizapp.backendservice.service

import com.quizapp.backendservice.domain.dto.request.ReqInsertQuestionDto
import com.quizapp.backendservice.domain.dto.request.ReqUpdateQuestionDto
import com.quizapp.backendservice.domain.dto.response.ResDetailQuestionDto
import com.quizapp.backendservice.domain.dto.response.ResGetQuestionDto
import com.quizapp.backendservice.domain.entity.MasterQuestionEntity

interface MasterQuestionService {
    fun createQuestion(request: ReqInsertQuestionDto): ResGetQuestionDto
    fun getAllQuestions(): List<ResGetQuestionDto>
    fun getQuestionById(id: Long): ResDetailQuestionDto?
    fun updateQuestion(req: ReqUpdateQuestionDto, id: Long) : ResGetQuestionDto
    fun softDeleteQuestion(id: Long): String
    fun hardDeleteQuestion(id: Long): String
}