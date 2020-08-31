package com.app.model.thymeleaf;

import com.app.model.Movie;
import com.app.model.Seance;
import com.app.model.User;
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
public class TicketWithObj {
    private Integer id;
    private Movie movie;
    private LocalDateTime date;
    private Integer rowNumber;
    private Integer placeNumber;
    private BigDecimal price;
    private BigDecimal discount;
    private User user;
}
