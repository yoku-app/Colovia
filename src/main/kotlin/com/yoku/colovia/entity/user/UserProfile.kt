package com.yoku.colovia.entity.user

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.yoku.colovia.entity.dto.UserDTO
import com.yoku.colovia.entity.dto.UserPartialDTO
import jakarta.persistence.*
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(
        name = "user_profiles",
        schema = "core",
        indexes = [
                Index(name = "idx_profiles_email", columnList = "email"),
                Index(name = "idx_profiles_name", columnList = "display_name")
        ]
)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
data class UserProfile(
        @Id
        @Column(name = "user_id", nullable = false) val userId: UUID,

        @Column(name = "phone") var phone: String? = null,

        @Column(name = "dob") var dob: ZonedDateTime?,

        @Enumerated(EnumType.STRING) @Column(name = "main_focus") var focus: Focus?,

        @Column(name = "email", nullable = false) var email: String,

        @Column(name = "display_name", nullable = false) var displayName: String,

        @Column(name = "avatar_url") var avatarUrl: String? = null,

        @Column(
                name = "created_at",
                nullable = false,
                updatable = false
        ) var createdAt: ZonedDateTime = ZonedDateTime.now(),

        @Column(name = "updated_at", nullable = false) var updatedAt: ZonedDateTime = ZonedDateTime.now(),

        @Embedded var onboardingCompletion: OnboardingCompletion? = null
) {
        @PrePersist
        fun onPrePersist() {
                createdAt = ZonedDateTime.now()
                updatedAt = ZonedDateTime.now()
        }

        @PreUpdate
        fun onPreUpdate() {
                updatedAt = ZonedDateTime.now()
        }

        @Embeddable
        @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
        data class OnboardingCompletion(
        @Column(name = "respondent_onboarding_completion") var respondent: ZonedDateTime? = null,

        @Column(name = "creator_onboarding_completion") var creator: ZonedDateTime? = null,

        @Column(name = "core_onboarding_completion") var core: ZonedDateTime? = null
        ){
                fun toDTO(): UserDTO.OnboardingCompletionDTO {
                        return UserDTO.OnboardingCompletionDTO(
                                respondent = this.respondent,
                                creator = this.creator,
                                core = this.core
                        )
                }
        }

        enum class Focus {
                RESPONDENT, CREATOR, HYBRID
        }

        fun toPartialDTO(): UserPartialDTO {
                return UserPartialDTO(
                        id = this.userId,
                        name = this.displayName,
                        dob = this.dob,
                        email = this.email,
                        avatarUrl = this.avatarUrl
                )
        }

        fun toDTO(): UserDTO {
                return UserDTO(
                        id = this.userId,
                        name = this.displayName,
                        phone = this.phone,
                        dob = this.dob,
                        email = this.email,
                        avatarUrl = this.avatarUrl,
                        focus = this.focus,
                        createdAt = this.createdAt,
                        onboardingCompletion = this.onboardingCompletion?.toDTO()
                )
        }
}
