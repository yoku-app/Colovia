package com.yoku.colovia.entity.dto

import com.yoku.colovia.entity.user.UserProfile
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.ZonedDateTime
import java.util.UUID

data class UserDTO(
    val id: UUID,
    val name: String,
    val phone: String?,
    val dob: ZonedDateTime?,
    val email: String,
    val avatarUrl: String?,
    @Enumerated(EnumType.STRING)
    val focus: UserProfile.Focus?,
    val onboardingCompletion: OnboardingCompletionDTO?,
    val createdAt: ZonedDateTime,
){
    data class OnboardingCompletionDTO(
        val respondent: ZonedDateTime?,
        val creator: ZonedDateTime?,
        val core: ZonedDateTime?,
    )
}

data class UserPartialDTO(
    val id: UUID,
    val name: String,
    val dob: ZonedDateTime?,
    val email: String,
    val avatarUrl: String?,
)