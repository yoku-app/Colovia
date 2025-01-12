package com.yoku.coreservice.Service;

import com.yoku.coreservice.Entity.User.UserProfile;
import com.yoku.coreservice.Exceptions.InvalidArgumentException;
import com.yoku.coreservice.Exceptions.UserNotFoundException;
import com.yoku.coreservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UtilService utilService;

    @Autowired
    private UserRepository userRepository;

    private UserProfile FindUserProfileOrThrow(String userId) throws UserNotFoundException {
        return userRepository.findByUserId(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException("User Profile not found with User Id:" + userId));
    }

    private UserProfile FindUserProfileOrThrow(UUID userId) throws UserNotFoundException {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User Profile not found with User Id:" + userId));
    }

    public UserProfile GetUserProfile(String userId) throws InvalidArgumentException, UserNotFoundException {
        // Validate UUID
        utilService.ValidateUUIDFromString(userId);

        // Find User Profile from Database else throw if null
        return FindUserProfileOrThrow(userId);

    }

    public UserProfile UpdateUserProfile(UserProfile user) throws InvalidArgumentException, UserNotFoundException{
        return userRepository.save(user);
    }



}
