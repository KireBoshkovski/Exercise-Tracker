package com.sorsix.exercisetracker.repository

import com.sorsix.exercisetracker.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    fun findByUsername(username: String): User?
}