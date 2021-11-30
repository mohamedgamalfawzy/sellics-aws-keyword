package com.sellics.aws.services.impl;

import com.sellics.aws.dto.AwsScoreResponse;
import com.sellics.aws.dto.AwsSuggestionsRequest;
import com.sellics.aws.dto.AwsSuggestionsResponse;
import com.sellics.aws.handlers.Handler;
import com.sellics.aws.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @implNote  The score service that is handles the request for calculations
 * @see com.sellics.aws.handlers.impl.ScoreHandler
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private Handler<AwsSuggestionsResponse,Integer> handler;

    /**
     * The calculateScore method to call the handler and calculate the score
     *
     * @param suggestions
     * @param request
     * @return AwsScoreResponse
     */
    @Override
    public AwsScoreResponse calculateScore(List<AwsSuggestionsResponse> suggestions, AwsSuggestionsRequest request) {
        int cumulativeScore= suggestions.stream().map(r-> handler.handle(r)).collect(Collectors.summingInt(Integer::intValue));
        int  score=cumulativeScore / request.getKeyword().length();
        return AwsScoreResponse.builder().keyword(request.getKeyword()).score(score).build();
    }
}
