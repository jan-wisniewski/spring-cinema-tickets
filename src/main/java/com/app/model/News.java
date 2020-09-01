package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class News {
    private Integer id;
    private String title;
    private String description;
    private String content;
    private String author;
    private LocalDateTime date;
    private String img;
}
