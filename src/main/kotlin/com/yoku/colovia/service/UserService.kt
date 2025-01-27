package com.yoku.colovia.service

import com.yoku.colovia.entity.user.UserProfile
import com.yoku.colovia.exceptions.InvalidArgumentException
import com.yoku.colovia.exceptions.UserNotFoundException
import com.yoku.colovia.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService(private val userRepository: UserRepository) {

    @Throws(UserNotFoundException::class)
    private fun findUserProfileOrThrow(userId: UUID): UserProfile {
        // Find User Profile from Database else throw if null
        return userRepository.findByUserId(userId).orElseThrow{UserNotFoundException("User not found with Id: $userId")}
    }

    @Throws(InvalidArgumentException::class, UserNotFoundException::class)
    fun getUserProfileById(userId: UUID): UserProfile {
        // Find User Profile from Database else throw if null
        return findUserProfileOrThrow(userId)
    }

    fun updateUserProfile(user: UserProfile): UserProfile {
        return userRepository.save(user)
    }
}
