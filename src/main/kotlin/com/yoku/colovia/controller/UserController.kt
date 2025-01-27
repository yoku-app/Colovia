package com.yoku.colovia.controller

import com.yoku.colovia.entity.user.UserProfile
import com.yoku.colovia.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PutMapping("/")
    fun updateUserProfile(@RequestBody userProfile: UserProfile): ResponseEntity<UserProfile> {
        val updatedUserProfile = userService.updateUserProfile(userProfile)
        return ResponseEntity.ok(updatedUserProfile)
    }

    @GetMapping("/id/{userId}")
    fun getUserProfileById(@PathVariable userId: UUID): ResponseEntity<UserProfile> {
        val userProfile = userService.getUserProfileById(userId)
        return ResponseEntity.ok(userProfile)
    }

    @GetMapping("/email/{email}")
    fun getUserProfileByEmail(@PathVariable email: String): ResponseEntity<UserProfile>{
        val userProfile = userService.getUserProfileByEmail(email)
        return ResponseEntity.ok(userProfile)
    }
}
