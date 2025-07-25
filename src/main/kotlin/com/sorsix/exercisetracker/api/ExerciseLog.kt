package com.sorsix.exercisetracker.api

import java.time.LocalDate

data class ExerciseLog(
    val date: LocalDate?,
    val duration: Long,
    val description: String
)
