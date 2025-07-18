package com.sorsix.exercisetracker.api

data class UserLogs(
    val id: String,
    val username: String,
    val count: Int,
    val log: List<ExerciseLog>
)
