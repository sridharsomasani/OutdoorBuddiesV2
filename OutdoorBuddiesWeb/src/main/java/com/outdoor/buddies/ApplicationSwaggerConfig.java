package com.outdoor.buddies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class ApplicationSwaggerConfig {

    @Bean
    public Docket outdoorBuddiesAPI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api.*"))
                .build()
                .apiInfo(apiMetaData());
    }

    private ApiInfo apiMetaData(){
        return new ApiInfoBuilder()
                .title("Outdoor Buddies REST API")
                .description("Outdoor Buddies REST API Backend services")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Sridhar Somasani", "https://github.com/sridharsomasani", "sridhar.somasani@gmail.com"))
                .build();
    }
}
