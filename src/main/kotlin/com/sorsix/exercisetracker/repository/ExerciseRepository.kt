package com.sorsix.exercisetracker.repository

import com.sorsix.exercisetracker.domain.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ExerciseRepository : JpaRepository<Exercise, Long>, JpaSpecificationExecutor<Exercise>