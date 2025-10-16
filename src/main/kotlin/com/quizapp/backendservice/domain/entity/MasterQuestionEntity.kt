package com.quizapp.backendservice.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "mst_question")
data class MasterQuestionEntity(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "mst_question_id_seq"
    )
    @SequenceGenerator(
        name = "mst_question_id_seq", // name used in @Genera  tedValue
        sequenceName = "mst_question_id_seq", // name of sequence in DB
        allocationSize = 1 // adjust based on how your DB increments
    )
    @Column(name = "id", insertable = false, updatable = false)
    var id: Long? = 0,

    @Column(nullable = false, name = "title")
    var title: String? = null,

    @Column(nullable = false, name = "option_a")
    var optionA: String? = null,

    @Column(nullable = false, name = "option_b")
    var optionB: String? = null,

    @Column(nullable = false, name = "option_c")
    var optionC: String? = null,

    @Column(nullable = false, name = "option_d")
    var optionD: String? = null,

    @Column(nullable = false, name = "correct_answer")
    var correctAnswer: String? = null,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: Timestamp? = null,

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = true)
    var updatedAt: Timestamp? = null,

    @Column(name = "deleted_at")
    var deletedAt: Timestamp? = null,

    @Column(name = "is_active")
    var isActive: Boolean = true,

    @Column(name = "is_delete")
    var isDelete: Boolean = false,
)
