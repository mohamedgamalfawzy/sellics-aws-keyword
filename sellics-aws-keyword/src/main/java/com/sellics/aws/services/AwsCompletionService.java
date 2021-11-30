package com.sellics.aws.services;

import com.sellics.aws.dto.AwsSuggestionsRequest;
import com.sellics.aws.dto.AwsSuggestionsResponse;

import java.util.List;

/**
 * @apiNote create aws connection and process all Completion services
 */
public interface AwsCompletionService {
    List<AwsSuggestionsResponse> getSuggestions(AwsSuggestionsRequest request);
}
