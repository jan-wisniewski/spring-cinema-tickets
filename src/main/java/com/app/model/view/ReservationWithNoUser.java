package com.app.model.view;

import com.app.enums.SeatState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationWithNoUser {
    private Integer seatSeanceId;
    private SeatState state;
    private Integer userId;
}
