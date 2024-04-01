package com.opendating.api.controller;

import com.opendating.api.model.UserProfile;
import com.opendating.api.model.UserProfileInput;
import com.opendating.api.service.UserProfileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

class UserProfileControllerTest {
    private static final String TEST_ID = "testId";
    private static final String TEST_NAME = "TestName";
    private static final String TEST_DESCRIPTION = "Test description";

    private static final UserProfile TEST_USER_PROFILE = UserProfile.builder()
            .id(TEST_ID).name(TEST_NAME).description(TEST_DESCRIPTION)
            .build();

    @Mock
    private UserProfileService userProfileService;

    @InjectMocks
    private UserProfileController userProfileController;

    private AutoCloseable mocks;

    @BeforeEach
    public void setup() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    public void testGetAllUserProfiles() {
        Mockito.when(userProfileService.getAllUserProfiles()).thenReturn(Collections.singletonList(TEST_USER_PROFILE));

        List<UserProfile> result = userProfileController.getAllUserProfiles();

        Assertions.assertEquals(Collections.singletonList(TEST_USER_PROFILE), result);
    }

    @Test
    public void testGetUserProfile() {
        Mockito.when(userProfileService.getUserProfile(Mockito.any())).thenReturn(TEST_USER_PROFILE);

        UserProfile result = userProfileController.getUserProfile(TEST_ID);

        Assertions.assertEquals(TEST_USER_PROFILE, result);
    }

    @Test
    public void testUpdateUserProfile() {
        Mockito.when(userProfileService.updateUserProfile(Mockito.any(), Mockito.any())).thenReturn(TEST_ID);

        String updatedProfileId = userProfileController.updateUserProfile(UserProfileInput.builder()
                .name(TEST_NAME)
                .description(TEST_DESCRIPTION)
                .build());

        Assertions.assertEquals(TEST_ID, updatedProfileId);
        Mockito.verify(userProfileService).updateUserProfile(Mockito.any(), Mockito.argThat(input ->
                TEST_NAME.equals(input.name()) && TEST_DESCRIPTION.equals(input.description())
        ));
    }

    @Test
    public void testDeleteUserProfile() {
        Mockito.when(userProfileService.deleteUserProfile(Mockito.any())).thenReturn(TEST_ID);

        String deletedProfileId = userProfileController.deleteUserProfile();

        Assertions.assertEquals(TEST_ID, deletedProfileId);
        Mockito.verify(userProfileService).deleteUserProfile(Mockito.any());
    }
}