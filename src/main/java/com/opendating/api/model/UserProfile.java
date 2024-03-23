package com.opendating.api.model;

import lombok.Builder;

@Builder
public record UserProfile(
        String id,
        String name,
        String description
) {
}
