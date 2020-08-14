package com.app.model.thymeleaf;

import com.app.model.CinemaRoom;
import com.app.model.Movie;
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
public class SeanceWithObj {
    private Integer id;
    private Movie movie;
    private CinemaRoom cinemaRoom;
    private LocalDateTime dateTime;
    private BigDecimal price;
}
