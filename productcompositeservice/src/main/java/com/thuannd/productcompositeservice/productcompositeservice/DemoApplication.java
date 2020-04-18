package com.thuannd.productcompositeservice.productcompositeservice;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import java.util.Collections;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static java.util.Collections.emptyList;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.thuannd")
@EnableSwagger2WebFlux
public class DemoApplication {

	@Value("${app.api.title}")
	private String title;
	@Value("${app.api.description}")
	private String description;
	@Value("${app.api.version}")
	private String version;
	@Value("${app.api.termofserviceurl}")
	private String termsOfServiceUrl;
	@Value("${app.api.license}")
	private String license;
	@Value("${app.api.licenseurl}")
	private String licenseUrl;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

	// config swagger
	@Bean
	public Docket apiDocument(){
		return new Docket(SWAGGER_2)
		.select()
		.apis(basePackage("com.thuannd"))
		.paths(PathSelectors.any())
		.build()
		.globalResponseMessage(RequestMethod.GET, Collections.emptyList())
		.apiInfo(new ApiInfo(title, description, version, termsOfServiceUrl, 
		new springfox.documentation.service.Contact("", "", ""), license, licenseUrl, 
		emptyList()));
	}

}
