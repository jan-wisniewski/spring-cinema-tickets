package com.app.validator;


import com.app.dto.CreateMovieDto;
import com.app.validator.validator.Validator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CreateMovieDtoValidator  implements Validator<CreateMovieDto> {

    @Override
    public Map<String, String> validate(CreateMovieDto item) {
        var errors = new HashMap<String, String>();
        if (isTitleLowercase(item)) {
            errors.put("Title", "Should starts with uppercase");
        }
        if (isTitleEmpty(item)) {
            errors.put("Title", "Title cannot be empty");
        }
        if (isDateToBeforeDateFrom(item)) {
            errors.put("Date", "Date to is before date from");
        }
        return errors;
    }

    private boolean isDateToBeforeDateFrom(CreateMovieDto item) {
        return LocalDateTime.parse(item.getDateTo()).isBefore(LocalDateTime.parse(item.getDateFrom()));
    }

    private boolean isTitleLowercase(CreateMovieDto item) {
        return item.getTitle().matches("[a-z]+");
    }

    private boolean isTitleEmpty(CreateMovieDto item) {
        return item.getTitle().equals("");
    }

}
