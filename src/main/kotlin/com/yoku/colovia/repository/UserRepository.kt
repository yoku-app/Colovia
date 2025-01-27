package com.yoku.colovia.repository

import com.yoku.colovia.entity.user.UserProfile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserProfile, UUID> {
    fun findByEmail(email: String): Optional<UserProfile>

}
