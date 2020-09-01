package com.app.model.thymeleaf;

import com.app.model.Cinema;
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
public class CityWithObj {
    private Integer id;
    private String name;
    private String img;
}
