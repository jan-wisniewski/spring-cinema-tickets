package com.app.model.thymeleaf.localDateAsString;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SeanceLocalDateAsString {
    private Integer movieId;
    private Integer cinemaRoomId;
    private String dateTime;
    private BigDecimal price;
}
