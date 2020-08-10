package com.app.validator;

import com.app.dto.CreateCinemaRoomDto;
import com.app.validator.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class CreateCinemaRoomDtoValidator implements Validator<CreateCinemaRoomDto> {

    @Override
    public Map<String, String> validate(CreateCinemaRoomDto item) {
        var errors = new HashMap<String, String>();
        if (isNameLowercase(item)) {
            errors.put("Name", "Should starts with uppercase");
        }
        if (isRowsNegativeOrEqualZero(item)) {
            errors.put("Rows", "Should be greater than 0");
        }
        if (isPlacesNegativeOrEqualZero(item)) {
            errors.put("Places", "Should be greater than 0");
        }
        if (isNameTooShort(item)) {
            errors.put("Name", "Should be longer than one char");
        }
        return errors;
    }

    private boolean isNameLowercase(CreateCinemaRoomDto cinemaRoomDto) {
        return cinemaRoomDto.getName().matches("[a-z]+");
    }

    private boolean isRowsNegativeOrEqualZero(CreateCinemaRoomDto cinemaRoomDto) {
        return cinemaRoomDto.getRows() < 1;
    }

    private boolean isPlacesNegativeOrEqualZero(CreateCinemaRoomDto cinemaRoomDto) {
        return cinemaRoomDto.getPlaces() < 1;
    }

    private boolean isNameTooShort(CreateCinemaRoomDto cinemaRoomDto) {
        return cinemaRoomDto.getName().length() < 2;
    }

}
