package com.app.validator;

import com.app.dto.CreateCityDto;
import com.app.validator.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class CreateCityDtoValidator  implements Validator<CreateCityDto> {

    @Override
    public Map<String, String> validate(CreateCityDto item) {
        var errors = new HashMap<String, String>();
        if (isStartsLowercase(item)) {
            errors.put("City","Name should starts from uppercase");
        }
        return errors;
    }

    private boolean isStartsLowercase(CreateCityDto cityDto) {
        return cityDto.getName().matches("[a-z].+");
    }

}
