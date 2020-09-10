package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing City entity in database
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class City {
    private Integer id;
    private String name;
    private String img;
}
