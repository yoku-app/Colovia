package com.yoku.colovia.service.user

import com.yoku.colovia.entity.dto.UserDTO
import com.yoku.colovia.entity.dto.UserPartialDTO
import com.yoku.colovia.entity.user.UserProfile
import com.yoku.colovia.exceptions.UserNotFoundException
import com.yoku.colovia.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val cachedUserService: CachedUserService
) {
    private val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    @Throws(UserNotFoundException::class)
    fun getUserProfileByEmail(email: String): UserProfile {
        return userRepository.findByEmail(email)
            .orElseThrow{UserNotFoundException("User not found with email: $email")}
    }

    @Throws(UserNotFoundException::class)
    fun getUserProfileById(userId: UUID): UserProfile {
        return cachedUserService.getUserProfileById(userId)
    }

    fun getBatchUserProfilesByIds(userIds: List<UUID>): Map<UUID, UserPartialDTO?> {
        return userIds.associateWith { userId ->
            try {
                cachedUserService.getUserProfileById(userId).toPartialDTO()
            } catch (e: UserNotFoundException) {
                logger.error("User not found with ID: $userId")
                null
            }
        }
    }

    @Throws(UserNotFoundException::class)
    fun updateUserProfile(user: UserDTO): UserProfile {
        return cachedUserService.updateUserProfile(user)
    }
}
