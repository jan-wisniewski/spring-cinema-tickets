package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object class for sharing required data concerning City entities
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateCityDto {
    /**
     * @param name of City
     */
    private String name;
    /**
     * @param link to image of the City
     */
    private String img;
}
