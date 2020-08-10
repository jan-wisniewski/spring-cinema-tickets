package com.app.validator;

import com.app.dto.CreateCinemaDto;
import com.app.validator.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class CreateCinemaDtoValidator implements Validator<CreateCinemaDto> {
    @Override
    public Map<String, String> validate(CreateCinemaDto item) {
        var errors = new HashMap<String, String>();
        if (isCinemaNameIsLowercase(item)) {
            errors.put("Name", "should starts with uppercase");
        }
        return errors;
    }

    private boolean isCinemaNameIsLowercase(CreateCinemaDto cinemaDto) {
        return cinemaDto.getName().matches("[a-z]+");
    }

}
