package com.yoku.colovia.service

import com.yoku.colovia.entity.user.UserProfile
import com.yoku.colovia.exceptions.UserNotFoundException
import com.yoku.colovia.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService(private val userRepository: UserRepository) {

    @Throws(UserNotFoundException::class)
    private fun findUserProfileByIdOrThrow(userId: UUID): UserProfile {
        // Find User Profile from Database else throw if null
        return userRepository.findById(userId).orElseThrow{UserNotFoundException("User not found with Id: $userId")}
    }

    @Throws(UserNotFoundException::class)
    private fun findUserProfileByEmailOrThrow(email: String): UserProfile {
        // Find User Profile from Database else throw if null
        return userRepository.findByEmail(email).orElseThrow{UserNotFoundException("User not found with Email: $email")}
    }

    @Throws(UserNotFoundException::class)
    fun getUserProfileById(userId: UUID): UserProfile {
        // Find User Profile from Database else throw if null
        return findUserProfileByIdOrThrow(userId)
    }

    @Throws(UserNotFoundException::class)
    fun getUserProfileByEmail(email: String): UserProfile {
        // Find User Profile from Database else throw if null
        return findUserProfileByEmailOrThrow(email)
    }

    fun updateUserProfile(user: UserProfile): UserProfile {
        return userRepository.save(user)
    }
}
