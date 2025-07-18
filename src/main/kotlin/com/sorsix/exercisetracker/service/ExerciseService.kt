package com.sorsix.exercisetracker.service

import com.sorsix.exercisetracker.api.ExerciseLog
import com.sorsix.exercisetracker.domain.Exercise
import com.sorsix.exercisetracker.domain.User
import com.sorsix.exercisetracker.repository.ExerciseRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExerciseService(
    private val userService: UserService,
    private val exerciseRepository: ExerciseRepository
) {
    fun addExerciseToUser(id: String, request: ExerciseLog): Exercise {
        return exerciseRepository.save(
            Exercise(
                date = request.date ?: Date(),
                duration = request.duration,
                description = request.description,
                user = userService.findById(id)
            )
        )
    }

    fun getLogsForUser(user: User): List<ExerciseLog> {
        return exerciseRepository.findAllByUser(user).map {
            ExerciseLog(date = it.date, duration = it.duration, description = it.description)
        }
    }
}