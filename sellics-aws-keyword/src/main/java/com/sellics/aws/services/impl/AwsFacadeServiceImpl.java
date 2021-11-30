package com.sellics.aws.services.impl;

import com.sellics.aws.dto.AwsScoreResponse;
import com.sellics.aws.dto.AwsSuggestionsRequest;
import com.sellics.aws.dto.AwsSuggestionsResponse;
import com.sellics.aws.services.AwsCompletionService;
import com.sellics.aws.services.AwsFacadeService;
import com.sellics.aws.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @implNote the facade service that combine all the aws calls and score calculator
 * @see ScoreServiceImpl
 * @see AwsCompletionServiceImp
 */
@Service
public class AwsFacadeServiceImpl implements AwsFacadeService {


    @Autowired
    AwsCompletionService completionService;
    @Autowired
    ScoreService scoreService;

    /**
     * request the keyword through the completion api and calculate the score through the score service
     *
     * @param keyword
     * @return AwsScoreResponse
     */
    @Override
    public AwsScoreResponse calculateScoreFromSuggestions(String keyword) {
        AwsSuggestionsRequest request=AwsSuggestionsRequest.builder().keyword(keyword).build();
        List<AwsSuggestionsResponse> responses= completionService.getSuggestions(request);
        return scoreService.calculateScore(responses,request);
    }
}
