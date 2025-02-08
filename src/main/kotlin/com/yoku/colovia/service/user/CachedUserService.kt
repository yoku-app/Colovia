package com.yoku.colovia.service.user

import com.yoku.colovia.entity.dto.UserDTO
import com.yoku.colovia.entity.user.UserProfile
import com.yoku.colovia.exceptions.UserNotFoundException
import com.yoku.colovia.repository.UserRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class CachedUserService(private val userRepository: UserRepository, private val redisTemplate: RedisTemplate<String, UserProfile>) {

    @Cacheable("user.profile", key = "#userId")
    @Throws(UserNotFoundException::class)
    fun getUserProfileById(userId: UUID): UserProfile {
        return userRepository.findById(userId)
            .orElseThrow{UserNotFoundException("User not found with ID: $userId")}
    }

    @Throws(UserNotFoundException::class)
    @CachePut("user.profile", key = "#user.id")
    fun updateUserProfile(user: UserDTO): UserProfile {
        val profile: UserProfile = userRepository.findById(user.id)
            .orElseThrow{UserNotFoundException("User not found with ID: ${user.id}")}

        profile.apply {
            this.displayName = user.name
            this.dob = user.dob
            this.email = user.email
            this.avatarUrl = user.avatarUrl
            this.phone = user.phone
            this.focus = user.focus
            this.onboardingCompletion = user.onboardingCompletion?.let {
                UserProfile.OnboardingCompletion(
                    respondent = it.respondent,
                    creator = it.creator,
                    core = it.core
                )
            }
        }

        return userRepository.save(profile)
    }

    @CacheEvict("user.profile", key = "#userId")
    fun deleteUserProfile(userId: UUID) {
        userRepository.deleteById(userId)
    }

    @CacheEvict("user.profile", key = "#userId")
    fun evictUserProfileCache(userId: UUID) {
        // This method will remove the cache for the given user ID
        redisTemplate.delete("user.profile::$userId")
    }
}