package com.opendating.api.controller;

import com.opendating.api.model.UserProfile;
import com.opendating.api.service.UserProfileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class UserProfileControllerTest {
    private static final String TEST_ID = "testId";
    private static final String TEST_NAME = "TestName";
    private static final String TEST_DESCRIPTION = "Test description";

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
    public void testUserProfile() {
        UserProfile testUserProfile = UserProfile.builder()
                .id(TEST_ID).name(TEST_NAME).description(TEST_DESCRIPTION)
                .build();
        Mockito.when(userProfileService.getUserProfile(Mockito.any())).thenReturn(testUserProfile);

        UserProfile result = userProfileController.getUserProfile(TEST_ID);

        Assertions.assertEquals(testUserProfile, result);
    }
}