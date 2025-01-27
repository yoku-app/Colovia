package com.yoku.colovia.repository

import com.yoku.colovia.entity.user.UserProfile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserProfile?, Long?> {
    fun findByUserId(userId: UUID?): Optional<UserProfile>
}
