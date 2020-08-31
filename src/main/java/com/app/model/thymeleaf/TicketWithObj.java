package com.app.model.thymeleaf;

import com.app.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TicketWithObj {
    private Integer id;
    private Movie movie;
    private LocalDateTime date;
    private Integer rowNumber;
    private Integer placeNumber;
    private User user;
    private City city;
    private Cinema cinema;
}
