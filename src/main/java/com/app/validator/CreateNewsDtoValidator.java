package com.app.validator;

import com.app.dto.CreateNewsDto;
import com.app.dto.CreateUserDto;
import com.app.validator.validator.Validator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CreateNewsDtoValidator implements Validator<CreateNewsDto> {

    @Override
    public Map<String, String> validate(CreateNewsDto item) {
        var errors = new HashMap<String, String>();
        if (!isTitleIsNotNull(item)) {
            errors.put("Title", "Can't be empty");
        }
        return errors;
    }

    private boolean isTitleIsNotNull(CreateNewsDto item) {
        return !item.getTitle().equals("");
    }

}
