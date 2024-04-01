package com.opendating.api.model;

import lombok.Builder;

@Builder
public record UserProfileInput(
        String name,
        String description
) {
}
