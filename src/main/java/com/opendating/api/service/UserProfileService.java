package com.opendating.api.service;

import com.opendating.api.model.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    public UserProfile getUserProfile(String id) {
        return new UserProfile(id, "Stub name", "Stub description");
    }
}
