package com.yoku.coreservice.Service;

import com.yoku.coreservice.Exceptions.InvalidArgumentException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilService {

    public void ValidateUUIDFromString(String uuid) throws InvalidArgumentException {
        try {
            UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Invalid UUID format");
        }
    }
}
