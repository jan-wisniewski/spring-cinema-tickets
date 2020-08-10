package com.app.model.view;

import com.app.enums.SeatState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SeatsSeanceWithSeanceDate {
    private Integer id;
    private Integer seatId;
    private SeatState seatState;
    private LocalDateTime dateTime;
}
