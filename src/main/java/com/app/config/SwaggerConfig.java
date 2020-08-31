package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // -------------------------------------------------------
    // CZESC 1
    // -------------------------------------------------------
    // Najprostsza konfiguracja
/*    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }*/

    // -------------------------------------------------------
    // CZESC 2
    // -------------------------------------------------------
    // Ustawiamy dane naglowka UI

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.app"))
              //  .paths(PathSelectors.ant("/workers/**")) // tylko dla workers
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("App for buying tickets, adding cinemas, seances and more!")
                .contact(new Contact("Jan Wiśniewski & Bogdan Ostaszewski", "http://www.janwisniewski.com", "kontakt@janwisniewski.com"))
                .license("Open License")
                .version("1.0")
                .licenseUrl("lic.pl")
                .title("Cinema Application")
                .build();
    }

    // -------------------------------------------------------
    // CZESC 3
    // -------------------------------------------------------
    // Dodajemy opis bledow

    /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.app.controller"))
                .paths(PathSelectors.any()) // tylko dla workers
                .build()
                .useDefaultResponseMessages(false) // wylaczamy domyslny generator opisu bledow
                .globalResponseMessage(
                        // dla wszystkich metod get masz dostarczyc opis bledow jak ponizej
                        RequestMethod.GET,
                        newArrayList(
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("GET 500 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("GET 404 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("GET 403 : To jest moj opis bledu")
                                        .build()
                                )
                )
                .globalResponseMessage(
                        // dla wszystkich metod get masz dostarczyc opis bledow jak ponizej
                        RequestMethod.POST,
                        newArrayList(
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("POST 500 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("POST 404 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("POST 403 : To jest moj opis bledu")
                                        .build()
                        )
                );
    }*/


    // -------------------------------------------------------
    // CZESC 4
    // -------------------------------------------------------
    // Dzielimy na grupy controllery
/*
    @Bean
    public Docket apiWorkers() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("WORKERS")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.app.controller"))
                .paths(PathSelectors.ant("/workers/**")) // tylko dla workers
                .build()
                .useDefaultResponseMessages(false) // wylaczamy domyslny generator opisu bledow
                .globalResponseMessage(
                        // dla wszystkich metod get masz dostarczyc opis bledow jak ponizej
                        RequestMethod.GET,
                        newArrayList(
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("GET 500 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("GET 404 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("GET 403 : To jest moj opis bledu")
                                        .build()
                        )
                )
                .globalResponseMessage(
                        // dla wszystkich metod get masz dostarczyc opis bledow jak ponizej
                        RequestMethod.POST,
                        newArrayList(
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("POST 500 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("POST 404 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("POST 403 : To jest moj opis bledu")
                                        .build()
                        )
                );
    }

    @Bean
    public Docket apiCompanies() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("COMPANIES")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.app.controller"))
                .paths(PathSelectors.ant("/companies/**")) // tylko dla workers
                .build()
                .useDefaultResponseMessages(false) // wylaczamy domyslny generator opisu bledow
                .globalResponseMessage(
                        // dla wszystkich metod get masz dostarczyc opis bledow jak ponizej
                        RequestMethod.GET,
                        newArrayList(
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("GET 500 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("GET 404 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("GET 403 : To jest moj opis bledu")
                                        .build()
                        )
                )
                .globalResponseMessage(
                        // dla wszystkich metod get masz dostarczyc opis bledow jak ponizej
                        RequestMethod.POST,
                        newArrayList(
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("POST 500 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("POST 404 : To jest moj opis bledu")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("POST 403 : To jest moj opis bledu")
                                        .build()
                        )
                );
    }
*/

}
