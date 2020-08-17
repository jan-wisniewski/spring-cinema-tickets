package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateSeanceDto {
    private Integer movieId;
    private Integer cinemaRoomId;
    private String dateTime;
    private BigDecimal price;
}
