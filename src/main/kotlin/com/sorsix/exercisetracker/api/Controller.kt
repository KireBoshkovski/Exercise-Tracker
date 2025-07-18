package com.sorsix.exercisetracker.api

import com.sorsix.exercisetracker.domain.User
import com.sorsix.exercisetracker.service.ExerciseService
import com.sorsix.exercisetracker.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class Controller(
    private val userService: UserService,
    private val exerciseService: ExerciseService
) {

    @GetMapping
    fun listAll(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(userService.listAll())
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
    fun getLogsForUser(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            val user = userService.findById(id)
            val logs = exerciseService.getLogsForUser(user)
            ResponseEntity.ok(UserLogs(user.id, user.username, logs.size, logs))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }
    }

}