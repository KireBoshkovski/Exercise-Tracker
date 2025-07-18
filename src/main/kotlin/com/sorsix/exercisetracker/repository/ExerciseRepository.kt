package com.sorsix.exercisetracker.repository

import com.sorsix.exercisetracker.domain.Exercise
import com.sorsix.exercisetracker.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository : JpaRepository<Exercise, Long> {
    fun findAllByUser(user: User): List<Exercise>
}