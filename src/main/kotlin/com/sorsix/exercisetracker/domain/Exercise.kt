package com.sorsix.exercisetracker.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "exercises")
data class Exercise(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val date: LocalDate,
    val duration: Long,
    val description: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)
