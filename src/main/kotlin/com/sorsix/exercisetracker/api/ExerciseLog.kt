package com.sorsix.exercisetracker.api

import java.util.*

data class ExerciseLog(
    val date: Date?,
    val duration: Long,
    val description: String
)
