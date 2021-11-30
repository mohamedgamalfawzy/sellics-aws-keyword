package com.sellics.aws.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class AwsSuggestionsResponse {
   private String alias;
   private String prefix;
   private String suffix;
   private String keyword;
   private List<SuggestionsKeyword> suggestions;
}
