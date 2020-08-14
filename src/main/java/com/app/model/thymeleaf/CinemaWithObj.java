package com.app.model.thymeleaf;

import com.app.model.CinemaRoom;
import com.app.model.City;
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
public class CinemaWithObj {
    private Integer id;
    private String name;
    private Integer cityId;
    private City city;
}
