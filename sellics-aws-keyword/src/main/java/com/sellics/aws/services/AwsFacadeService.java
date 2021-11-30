package com.sellics.aws.services;

import com.sellics.aws.dto.AwsScoreResponse;

public interface AwsFacadeService {
    AwsScoreResponse calculateScoreFromSuggestions(String keyword);
}
