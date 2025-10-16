package com.quizapp.backendservice.controller

import com.quizapp.backendservice.domain.dto.request.ReqInsertQuestionDto
import com.quizapp.backendservice.domain.dto.request.ReqUpdateQuestionDto
import com.quizapp.backendservice.domain.dto.response.BaseResponse
import com.quizapp.backendservice.domain.dto.response.ResDetailQuestionDto
import com.quizapp.backendservice.domain.dto.response.ResGetQuestionDto
import com.quizapp.backendservice.domain.entity.MasterQuestionEntity
import com.quizapp.backendservice.service.MasterQuestionService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/questions")
class QuestionController(
    private val masterQuestionService: MasterQuestionService
) {
    @Transactional
    @PostMapping("/insert")
    fun createQuestion(
        @Valid
        @RequestBody request: ReqInsertQuestionDto
    ): ResponseEntity<BaseResponse<ResGetQuestionDto>> {
        val response = masterQuestionService.createQuestion(request)
        return ResponseEntity.ok(
            BaseResponse(
                data = response
            )
        )
    }

    @GetMapping("/active")
    fun getAllActiveQuestions(): ResponseEntity<BaseResponse<List<ResGetQuestionDto>>> {
        val response = masterQuestionService.getAllQuestions()
        return ResponseEntity.ok(
            BaseResponse(
                data = response
            )
        )
    }

    @GetMapping("/{id}")
    fun getQuestionById(
        @PathVariable id: Long
    ): ResponseEntity<BaseResponse<ResDetailQuestionDto>> {
        val response = masterQuestionService.getQuestionById(id)
        return ResponseEntity.ok(
            BaseResponse(
                data = response
            )
        )
    }

    @Transactional
    @PutMapping("/{id}/update")
    fun updateQuestion(
        @Valid
        @RequestBody request : ReqUpdateQuestionDto,
        @PathVariable id: Long
    ): ResponseEntity<BaseResponse<ResGetQuestionDto>> {
        val response = masterQuestionService.updateQuestion(request, id)
        return ResponseEntity.ok(
            BaseResponse(
                data = response
            )
        )
    }

    @DeleteMapping("/{id}/soft-delete")
    fun softDeleteQuestion(
        @PathVariable id: Long
    ): ResponseEntity<BaseResponse<String>> {
        return ResponseEntity.ok(
            BaseResponse(
                data = masterQuestionService.softDeleteQuestion(id)
            )
        )
    }

    @DeleteMapping("/{id}/hard-delete")
    fun hardDeleteQuestion(
        @PathVariable id: Long
    ): ResponseEntity<BaseResponse<String>> {
        return ResponseEntity.ok(
            BaseResponse(
                data = masterQuestionService.hardDeleteQuestion(id)
            )
        )
    }
}
