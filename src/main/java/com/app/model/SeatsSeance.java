package com.app.model;

import com.app.enums.SeatState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SeatsSeance {
    private Integer id;
    private Integer seatId;
    private Integer seanceId;
    private SeatState state;
}
