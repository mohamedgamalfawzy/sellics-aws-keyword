package com.sellics.aws.controllers;

import com.sellics.aws.dto.ApiErrorResponse;
import com.sellics.aws.dto.AwsScoreResponse;
import com.sellics.aws.services.AwsFacadeService;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/estimate")
@Api(tags = "estimate")
public class EstimateController {
    @Autowired
    AwsFacadeService facade;
    @Autowired
    private TimeLimiter timeLimiter;

    @GetMapping
    @ApiOperation(value = "get estimate sore for keyword ")
    public Callable<ResponseEntity<AwsScoreResponse>> getSuggestions(@RequestParam String keyword){
        return TimeLimiter.decorateFutureSupplier(timeLimiter, () ->
                CompletableFuture.supplyAsync(() -> {
                    if (keyword==null || keyword.isEmpty()) return new ResponseEntity(ApiErrorResponse.builder().status(HttpStatus.BAD_REQUEST)
                            .message("keyword cannot be empty").timestamp(LocalDateTime.now()).build(), HttpStatus.BAD_REQUEST);
                    return new ResponseEntity<>( facade.calculateScoreFromSuggestions(keyword.trim()),HttpStatus.OK);
                })
        );
    }
}
