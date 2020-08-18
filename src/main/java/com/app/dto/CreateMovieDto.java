package com.app.dto;

import com.app.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateMovieDto {
    private String title;
    private Genre genre;
    private String description;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
}
