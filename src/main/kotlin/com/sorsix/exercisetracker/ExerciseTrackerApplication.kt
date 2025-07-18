package com.sorsix.exercisetracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExerciseTrackerApplication

fun main(args: Array<String>) {
    runApplication<ExerciseTrackerApplication>(*args)
}
