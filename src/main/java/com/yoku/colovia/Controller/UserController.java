package com.yoku.colovia.Controller;

import com.yoku.colovia.Entity.User.UserProfile;
import com.yoku.colovia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/")
    public ResponseEntity<UserProfile> UpdateUserProfile(@RequestBody UserProfile userProfile) {
        UserProfile updatedUserProfile = userService.UpdateUserProfile(userProfile);
        return ResponseEntity.ok(updatedUserProfile);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfile> GetUserProfile(@PathVariable String userId) {
        UserProfile userProfile = userService.GetUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }

}
