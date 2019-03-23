package com.stackroute.automaticanswersearchservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    // docket bean to configure Swagger2 for the application.
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                select() method called on the docket bean instance returns an APISelectorBuilder,
//                which provides the apis() and paths() methods
                .select()
//        RequestHandlerSelectors.basePackage predicate matches the current base package to filter the API
                .apis(RequestHandlerSelectors.basePackage("com.stackroute.automaticanswersearchservice"))
                .paths(regex("/rest/question.*"))
                .build()
                .apiInfo(metaData());
    }

    //metaData() method provides the information about the api
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API for Online Store\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("eroteme", "https://github.com/stackroute/boeing-wave4-eroteme/", "shaliniganesh330"))
                .build();
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
