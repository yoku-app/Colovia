package com.yoku.colovia.controller

import com.yoku.colovia.entity.dto.UserDTO
import com.yoku.colovia.entity.dto.UserPartialDTO
import com.yoku.colovia.entity.user.UserProfile
import com.yoku.colovia.exceptions.UnauthorizedException
import com.yoku.colovia.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PutMapping("/")
    fun updateUserProfile(@RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        val updatedUserProfile = userService.updateUserProfile(user)
        return ResponseEntity.ok(updatedUserProfile.toDTO())
    }

    /**
     * Fetches the complete user profile by its Id, including private information
     * this should only be used when fetching the user's own profile
     */
    @GetMapping("/session")
    fun getUserProfileById(@RequestHeader("X-User-Id") userId: UUID?): ResponseEntity<UserDTO>{
        if(userId == null)  throw UnauthorizedException("User Id was not provider in request headers")

        val userProfile = userService.getUserProfileById(userId)
        return ResponseEntity.ok(userProfile.toDTO())
    }

    /**
     * Fetches a publicly available User Profile by its associated ID
     */
    @GetMapping("/display/id/{userId}")
    fun getUserDisplayById(@PathVariable userId: UUID): ResponseEntity<UserPartialDTO> {
        val userProfile = userService.getUserProfileById(userId)
        return ResponseEntity.ok(userProfile.toPartialDTO())
    }

    /**
     * Fetches many publicly available User Profiles by their associated IDs
     */
    @GetMapping("/display/ids")
    fun getBatchUserDisplayByIds(@RequestParam userIds: List<UUID>): ResponseEntity<Map<UUID, UserPartialDTO?>> {
        val userProfiles = userService.getBatchUserProfilesByIds(userIds)
        return ResponseEntity.ok(userProfiles)
    }

    @GetMapping("/email/{email}")
    fun getUserProfileByEmail(@PathVariable email: String): ResponseEntity<UserDTO>{
        val userProfile = userService.getUserProfileByEmail(email)
        return ResponseEntity.ok(userProfile.toDTO())
    }
}
