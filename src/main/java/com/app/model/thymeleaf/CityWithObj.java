package com.app.model.thymeleaf;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Builder class used in CityController to build an object which is understandable for thymeleaf
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CityWithObj {
    private Integer id;
    private String name;
    private String img;
}
