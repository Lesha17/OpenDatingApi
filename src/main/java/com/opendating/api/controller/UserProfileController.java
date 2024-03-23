package com.opendating.api.controller;

import com.opendating.api.model.UserProfile;
import com.opendating.api.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/userProfile")
@RequiredArgsConstructor
public class UserProfileController {

    @Qualifier("userProfileService")
    private final UserProfileService userProfileService;

    @GetMapping("/{id}")
    public UserProfile getUserProfile(@PathVariable String id) {
        return userProfileService.getUserProfile(id);
    }
}
