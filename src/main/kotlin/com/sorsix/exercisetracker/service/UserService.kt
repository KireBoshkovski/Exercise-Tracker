package com.sorsix.exercisetracker.service

import com.sorsix.exercisetracker.domain.User
import com.sorsix.exercisetracker.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun findById(id: String): User {
        return userRepository.findByIdOrNull(id)
            ?: throw RuntimeException("User with id $id not found")
    }

    fun addUser(username: String): User {
        return try {
            userRepository.save(User(username = username))
        } catch (e: DataIntegrityViolationException) {
            throw RuntimeException("Username '$username' is already taken")
        }
    }
}