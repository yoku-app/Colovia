package com.yoku.coreservice.Controller;

import com.yoku.coreservice.Entity.User.UserProfile;
import com.yoku.coreservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfile> GetUserProfile(@PathVariable String userId) {
            UserProfile userProfile = userService.GetUserProfile(userId);
            return ResponseEntity.ok(userProfile);

    }


}
