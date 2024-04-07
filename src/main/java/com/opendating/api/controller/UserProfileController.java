package com.opendating.api.controller;

import com.opendating.api.model.UserProfileInput;
import com.opendating.api.model.UserProfile;
import com.opendating.api.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/userProfile")
@RequiredArgsConstructor
public class UserProfileController {

    @Qualifier("userProfileService")
    private final UserProfileService userProfileService;

    @GetMapping("getAll")
    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }

    @GetMapping("/get/{id}")
    public UserProfile getUserProfile(@PathVariable String id) {
        return userProfileService.getUserProfile(id);
    }

    @PostMapping("/update")
    public String updateUserProfile(@RequestBody UserProfileInput input) {
        String userProfileId = "userProfileIdFetchedFromJwt";
        return userProfileService.updateUserProfile(userProfileId, input);
    }

    @GetMapping("/delete")
    public String deleteUserProfile() {
        String userProfileId = "userProfileIdFetchedFromJwt";
        if(userProfileService.deleteUserProfile(userProfileId)) {
            return "OK";
        } else {
            return "Error";
        }
    }
}
