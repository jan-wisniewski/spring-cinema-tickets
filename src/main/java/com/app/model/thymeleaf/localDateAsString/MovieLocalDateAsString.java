package com.app.model.thymeleaf.localDateAsString;

import com.app.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MovieLocalDateAsString {
    private Integer id;
    private String title;
    private Genre genre;
    private String description;
    private String dateFrom;
    private String dateTo;
    private String img;
}