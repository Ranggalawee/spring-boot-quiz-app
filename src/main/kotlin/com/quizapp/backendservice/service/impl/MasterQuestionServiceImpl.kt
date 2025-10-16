package com.quizapp.backendservice.service.impl

import com.quizapp.backendservice.domain.dto.request.ReqInsertQuestionDto
import com.quizapp.backendservice.domain.dto.request.ReqUpdateQuestionDto
import com.quizapp.backendservice.domain.dto.response.ResDetailQuestionDto
import com.quizapp.backendservice.domain.dto.response.ResGetQuestionDto
import com.quizapp.backendservice.domain.entity.MasterQuestionEntity
import com.quizapp.backendservice.exception.CustomException
import com.quizapp.backendservice.repository.MasterQuestionRepository
import com.quizapp.backendservice.service.MasterQuestionService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.Date
import kotlin.String

@Service
class MasterQuestionServiceImpl(
    private val masterQuestionRepository: MasterQuestionRepository
): MasterQuestionService {
    override fun createQuestion(request: ReqInsertQuestionDto): ResGetQuestionDto {

        val rawData = MasterQuestionEntity(
            title = request.title,
            optionA = request.optionA,
            optionB = request.optionB,
            optionC = request.optionC,
            optionD = request.optionD,
            correctAnswer = request.correctAnswer
        )

        val question = masterQuestionRepository.save(rawData)
        return ResGetQuestionDto(
            id = question.id.toString(),
            title = question.title,
            optionA = question.optionA,
            optionB = question.optionB,
            optionC = question.optionC,
            optionD = question.optionD,
            correctAnswer = question.correctAnswer
        )
    }

    override fun getAllQuestions(): List<ResGetQuestionDto> {
        val rawData = masterQuestionRepository.getAllActiveQuestion()
        val result = mutableListOf<ResGetQuestionDto>()
        rawData.forEach { question ->
            result.add(
                ResGetQuestionDto(
                    id = question.id.toString(),
                    title = question.title,
                    optionA = question.optionA,
                    optionB = question.optionB,
                    optionC = question.optionC,
                    optionD = question.optionD,
                    correctAnswer = question.correctAnswer
                )
            )
        }
        return result
    }

    override fun getQuestionById(id: Long): ResDetailQuestionDto? {
        val rawData = masterQuestionRepository.findById(id).orElse(null)
        if (rawData == null) {
            throw CustomException(exceptionMessage = "Question $id not found", statusCode = 404)
        }

        return ResDetailQuestionDto(
            id = rawData.id.toString(),
            title = rawData.title,
            optionA = rawData.optionA,
            optionB = rawData.optionB,
            optionC = rawData.optionC,
            optionD = rawData.optionD,
            correctAnswer = rawData.correctAnswer,
            isActive = rawData.isActive,
            isDelete = rawData.isDelete,
            createdAt = rawData.createdAt.toString(),
            updatedAt = rawData.updatedAt.toString(),
            deletedAt = rawData.deletedAt.toString()
        )
    }

    override fun updateQuestion(
        req: ReqUpdateQuestionDto,
        id: Long
    ): ResGetQuestionDto {
        val question = masterQuestionRepository.findById(id).orElseThrow {
            throw CustomException(
                "Question id $id not found",
                HttpStatus.NOT_FOUND.value()
            )
        }

        question.title = req.title
        question.optionA = req.optionA
        question.optionB = req.optionB
        question.optionC = req.optionC
        question.optionD = req.optionD
        question.correctAnswer = req.correctAnswer

        val result = masterQuestionRepository.save(question)

        return ResGetQuestionDto(
            id = result.id.toString(),
            title = result.title,
            optionA = result.optionA,
            optionB = result.optionB,
            optionC = result.optionC,
            optionD = result.optionD,
            correctAnswer = result.correctAnswer
        )
    }

    override fun softDeleteQuestion(id: Long): String {
        val rawData = masterQuestionRepository.findById(id).orElse(null)
        if (rawData == null) {
            throw CustomException(
                "Question id $id not found",
                HttpStatus.NOT_FOUND.value()
            )
        }

        if (rawData.isDelete) {
            throw CustomException("Question already deleted", 400)
        }

        rawData.isDelete = true
        rawData.isActive = false
        rawData.deletedAt = Timestamp(Date().time)
        masterQuestionRepository.save(rawData)
        return "Question with ID $id has been soft deleted"
    }

    override fun hardDeleteQuestion(id: Long): String {
        val rawData = masterQuestionRepository.findById(id).orElse(null)
        if (rawData == null) {
            throw CustomException(
                "Question id $id not found",
                HttpStatus.NOT_FOUND.value()
            )
        }

        masterQuestionRepository.deleteById(id)
        return "Question with ID $id has been permanently deleted"
    }
}
