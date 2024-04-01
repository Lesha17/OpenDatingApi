package com.opendating.api.service;

import com.opendating.api.model.UserProfileInput;
import com.opendating.api.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserProfileService {
    public List<UserProfile> getAllUserProfiles() {
        return Collections.singletonList(getUserProfile("id"));
    }

    public UserProfile getUserProfile(String id) {
        return new UserProfile(id, "Stub name", "Stub description");
    }

    public String updateUserProfile(String profileId, UserProfileInput input) {
        return "profileId";
    }

    public String deleteUserProfile(String profileId) {
        return "deletedProfileId";
    }
}
