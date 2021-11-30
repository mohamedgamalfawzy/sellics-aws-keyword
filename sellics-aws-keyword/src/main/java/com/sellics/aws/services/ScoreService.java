package com.sellics.aws.services;

import com.sellics.aws.dto.AwsScoreResponse;
import com.sellics.aws.dto.AwsSuggestionsRequest;
import com.sellics.aws.dto.AwsSuggestionsResponse;

import java.util.List;

/**
 * @apiNote Calculate the score from the suggestions list
 * @see com.sellics.aws.services.impl.AwsCompletionServiceImp
 */
public interface ScoreService {
    AwsScoreResponse calculateScore(List<AwsSuggestionsResponse> suggestions, AwsSuggestionsRequest request);
}
