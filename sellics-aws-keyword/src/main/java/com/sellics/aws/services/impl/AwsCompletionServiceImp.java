package com.sellics.aws.services.impl;

import com.sellics.aws.dto.AwsSuggestionsRequest;
import com.sellics.aws.dto.AwsSuggestionsResponse;
import com.sellics.aws.services.AwsCompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.sellics.aws.constants.AppConstants.*;
import static java.util.stream.Collectors.toList;

@Service
public class AwsCompletionServiceImp implements AwsCompletionService {
    @Autowired
    private RestTemplate httpTemplate;
    @Value("${aws.host}")
    public  String HOST;
    @Value("${aws.resource}")
    public  String RESOURCE;

    /**
     * Asynchronously  call the aws Completion api to get the suggestions.
     * Divided the prefix into char array and search for each character incrementally (e.g. i,ip,iph,ipho,iphon,iphone)
     * @param request request
     * @return List of Aws Suggestions
     */
    @Override
    public List<AwsSuggestionsResponse> getSuggestions(AwsSuggestionsRequest request) {
        List<String> values=new ArrayList<>();
        int i=0, j=0;
        while(j <= request.getKeyword().length()){
            values.add(request.getKeyword().substring(i,j));
            j++;
        }

        List<CompletableFuture<AwsSuggestionsResponse>> responses= values.stream().filter(r-> !r.isEmpty())
                .map(prefix -> CompletableFuture.supplyAsync(()-> {
                    String url=HOST+RESOURCE+"?"+PREFIX+prefix+"&"+MID+"ATVPDKIKX0DER&"+ALIAS+"aps";
                    AwsSuggestionsResponse response = httpTemplate.getForEntity(url
                            , AwsSuggestionsResponse.class).getBody();
                    response.setKeyword(request.getKeyword());
                    return response;
                }))
                .collect(toList());
        CompletableFuture.allOf(responses.toArray(new CompletableFuture[0])).join();
        return responses.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}
