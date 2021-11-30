package com.sellics.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
public class SellicsAwsKeywordApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellicsAwsKeywordApplication.class, args);
	}
	@Bean
	public Docket Swagger() {
		ApiInfo apiInfo = new ApiInfo("ESTIMATE_API",
				"Microservice to estimate the search-volume of amazon search keywords.", "1.0", null, null, null,
				null, Collections.emptyList());
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.sellics.aws")).build().apiInfo(apiInfo);
	}
}
