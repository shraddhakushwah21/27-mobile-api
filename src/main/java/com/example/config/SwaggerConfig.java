package com.example.config;


import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig { 
	
	@Bean
	public Docket createDocket() {
		Docket docket=new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.controller"))
				.paths(PathSelectors.any()).build()
				.apiInfo(getApiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.useDefaultResponseMessages(false);
		return docket;
	}
	
	public ApiInfo getApiInfo() {
		Contact contact=new Contact("Shraddha", "https://example.com", "socialprogram42@gmail.com");
		 ApiInfo apiInfo = new ApiInfoBuilder()
				 .title("Spring Boot REST API")
				 .description("Spring Boot REST API for Mobile")
				 .version("1.0")
				 .termsOfServiceUrl("Terms of service")
				 .contact(contact)
				 .license( "Apache License Version 2.0")
				 .licenseUrl( "https://www.apache.org/licenses/LICENSE-2.0")
				 .build();
	        return apiInfo;

	}
	
	public ApiKey apiKey() {
		return new ApiKey("jwtToken", "Authorization", "header");
	}
	
	@Bean
	public SecurityContext securityContext() {
	    return SecurityContext.builder()
	            .securityReferences(defaultAuth())
	            .forPaths(PathSelectors.any())
	            .build();
	}

	private List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope
	            = new AuthorizationScope("global", "accessEverything");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    return Arrays.asList(new SecurityReference("jwtToken", authorizationScopes));
	}
}
