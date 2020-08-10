package com.app.dto;

import com.app.enums.SeatState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateSeatSeanceDto {
    private Integer seatId;
    private Integer seanceId;
    private SeatState state;
}
