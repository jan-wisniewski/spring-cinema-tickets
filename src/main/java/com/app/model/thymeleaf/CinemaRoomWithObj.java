package com.app.model.thymeleaf;

import com.app.model.Cinema;
import com.app.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CinemaRoomWithObj {
    private Integer id;
    private String name;
    private Integer rowsNumber;
    private Integer places;
    private Cinema cinema;
}
