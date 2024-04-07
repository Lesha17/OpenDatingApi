package com.opendating.api.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.opendating.api.model.UserProfileInput;
import com.opendating.api.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private static final String OPENDATING_DATABASE = "opendating";
    private static final String PROFILES_COLLECTION = "profiles";

    @Qualifier("mongoClient")
    private final MongoClient mongoClient;

    public List<UserProfile> getAllUserProfiles() {
        List<UserProfile> result = new ArrayList<>();
        getUserProfilesCollection()
                .find()
                .into(result);
        return result;
    }

    public UserProfile getUserProfile(String id) {
        return getUserProfilesCollection()
                .find(Filters.eq("_id", new ObjectId(id)))
                .first();
    }

    public String updateUserProfile(String profileId, UserProfileInput input) {
        return getUserProfilesCollection().updateOne(Filters.eq("_id", profileId), List.of(
                Updates.set("name", input.name()),
                Updates.set("description", input.description())))
                .getUpsertedId().asString().getValue();
    }

    public boolean deleteUserProfile(String profileId) {
        return getUserProfilesCollection().deleteOne(Filters.eq("_id", profileId))
                .wasAcknowledged();
    }

    private MongoCollection<UserProfile> getUserProfilesCollection() {
        return mongoClient.getDatabase(OPENDATING_DATABASE).getCollection(PROFILES_COLLECTION, UserProfile.class);
    }
}
