package org.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Blog-Rest-API")
				.description("API reference for developers")
				.termsOfServiceUrl("http://abc.com")
				.contact("demo@gmail.com").license("Demo License")
				.licenseUrl("demo@gmail.com").version("1.0").build();
	}
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(PathSelectors.any()).build();
	}
	
}
