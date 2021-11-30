package com.sellics.aws.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Builder
public class AwsScoreResponse {
    private String keyword;
    private int score;
}
