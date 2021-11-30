package com.sellics.aws.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SuggestionsKeyword {
    private String suggType;
    private String type;
    private String value;
    private String refTag;
    private String candidateSources;
    private String strategyId;

}
