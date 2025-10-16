package com.quizapp.backendservice.repository

import com.quizapp.backendservice.domain.entity.MasterQuestionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MasterQuestionRepository : JpaRepository<MasterQuestionEntity, Long> {
    @Query("""
            SELECT U FROM MasterQuestionEntity U
            WHERE U.isDelete = false
            AND U.isActive = true
        """, nativeQuery = false)
    fun getAllActiveQuestion(): List<MasterQuestionEntity>
}
