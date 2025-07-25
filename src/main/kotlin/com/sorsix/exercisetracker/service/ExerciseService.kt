package com.sorsix.exercisetracker.service

import com.sorsix.exercisetracker.api.ExerciseLog
import com.sorsix.exercisetracker.domain.Exercise
import com.sorsix.exercisetracker.domain.User
import com.sorsix.exercisetracker.repository.ExerciseRepository
import com.sorsix.exercisetracker.service.specification.FieldFilterSpecification
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ExerciseService(
    private val userService: UserService,
    private val exerciseRepository: ExerciseRepository
) {
    fun addExerciseToUser(id: String, request: ExerciseLog): Exercise {
        return exerciseRepository.save(
            Exercise(
                date = request.date ?: LocalDate.now(),
                duration = request.duration,
                description = request.description,
                user = userService.findById(id)
            )
        )
    }

    fun getLogsForUser(user: User, from: LocalDate?, to: LocalDate?, limit: Int?): List<ExerciseLog> {
        var spec = FieldFilterSpecification.filterEqualsT(Exercise::class.java, "user", user)

        FieldFilterSpecification.greaterThan(Exercise::class.java, "date", from)?.let {
            spec = spec?.and(it) ?: it
        }

        FieldFilterSpecification.lessThan(Exercise::class.java, "date", to)?.let {
            spec = spec?.and(it) ?: it
        }

        val pageable = if (limit != null) PageRequest.of(0, limit) else Pageable.unpaged()

        return exerciseRepository.findAll(spec, pageable).map {
            ExerciseLog(date = it.date, duration = it.duration, description = it.description)
        }.toList()
    }
}