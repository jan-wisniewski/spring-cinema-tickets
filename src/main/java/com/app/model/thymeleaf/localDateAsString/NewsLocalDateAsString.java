package com.app.model.thymeleaf.localDateAsString;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NewsLocalDateAsString {
    private Integer id;
    private String title;
    private String description;
    private String content;
    private String author;
    private String date;
    private String img;
}
