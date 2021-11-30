package com.sellics.aws.handlers.impl;

import com.sellics.aws.dto.AwsSuggestionsResponse;
import com.sellics.aws.handlers.Handler;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.stereotype.Component;


/**
 *@implNote The Score Handler to handle the calculations of the score
 */
@Component
public class ScoreHandler implements Handler<AwsSuggestionsResponse,Integer> {
    /**
     * handles the calculations of the score by using FuzzySearch to calculate the similarity of each prefix
     * @param entity
     * @return
     */
    @Override
    public Integer handle(AwsSuggestionsResponse entity) {
        int score= entity.getSuggestions().stream().map(r -> calculateScore(entity.getKeyword(), r.getValue())).mapToInt(Integer::intValue).sum();
        return score / 10;
    }

    /**
     * FuzzySearch to calculate the similarity of each prefix by getting the ratio of the similarity for each character
     * TODO - calculate the score based on its rank along with the FuzzySearch ratio
     *
     * @param prefix
     * @param suggestion
     * @return score
     */
    private int calculateScore(String prefix,String suggestion){
        return   FuzzySearch.ratio(prefix,suggestion);
    }
}
