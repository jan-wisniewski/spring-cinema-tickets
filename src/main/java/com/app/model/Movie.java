package com.app.model;

import com.app.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Movie {
    private Integer id;
    private String title;
    private Genre genre;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
}
