package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Seat {
    private Integer id;
    private Integer rowsNumber;
    private Integer place;
    private Integer cinemaRoomId;
}
