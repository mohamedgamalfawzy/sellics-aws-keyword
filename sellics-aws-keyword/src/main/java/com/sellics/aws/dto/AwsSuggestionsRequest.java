package com.sellics.aws.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class AwsSuggestionsRequest {
    private String keyword;
}
