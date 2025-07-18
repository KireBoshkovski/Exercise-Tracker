package com.sorsix.exercisetracker.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "app_users")
data class User(
    @Id
    @Column(name = "user_id")
    val id: String = UUID.randomUUID().toString(),
    @Column(unique = true, nullable = false)
    val username: String
)
