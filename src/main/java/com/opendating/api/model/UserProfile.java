package com.opendating.api.model;

import lombok.Builder;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;


@Builder
public record UserProfile(
        @BsonId()
        @BsonRepresentation(BsonType.OBJECT_ID)
        String id,
        String name,
        String description
) {
}
