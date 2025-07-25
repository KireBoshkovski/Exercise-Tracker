package com.sorsix.exercisetracker.api

import com.sorsix.exercisetracker.domain.User
import com.sorsix.exercisetracker.repository.UserRepository
import com.sorsix.exercisetracker.service.ExerciseService
import com.sorsix.exercisetracker.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/users")
class Controller(
    private val userService: UserService,
    private val exerciseService: ExerciseService,
    private val userRepository: UserRepository
) {

    @GetMapping
    fun listAll(): List<User> {
        return userRepository.findAll()
    }

    @PostMapping
    fun registerUser(@RequestParam username: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.addUser(username))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }
    }

    @PostMapping("/{id}/exercises")
    fun addExercise(@PathVariable id: String, @RequestBody exerciseLog: ExerciseLog): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(exerciseService.addExerciseToUser(id, exerciseLog))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }
    }

    @GetMapping("/{id}/logs")
    fun getLogsForUser(
        @PathVariable id: String,
        @RequestParam(required = false) from: LocalDate?,
        @RequestParam(required = false) to: LocalDate?,
        @RequestParam(required = false) limit: Int?
    ): ResponseEntity<Any> {
        return try {
            val user = userService.findById(id)
            val logs = exerciseService.getLogsForUser(user, from, to, limit)
            ResponseEntity.ok(UserLogs(user.id, user.username, logs.size, logs))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }
    }

}