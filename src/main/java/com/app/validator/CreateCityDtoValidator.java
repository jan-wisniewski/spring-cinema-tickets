package com.app.validator;

import com.app.dto.CreateCityDto;
import com.app.validator.validator.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * class implementing Validator in order to validate if the pramaters in new City object are correct to requirements
 */
public class CreateCityDtoValidator  implements Validator<CreateCityDto> {

    /***
     * The method validates if City object parameters are valid
     *
     * @param item object which is validated inside the method
     * @return
     */
    @Override
    public Map<String, String> validate(CreateCityDto item) {
        var errors = new HashMap<String, String>();
        if (isStartsLowercase(item)) {
            errors.put("City","Name should starts from uppercase");
        }
        return errors;
    }

    /***
     * the method checks if Name of new City starts from lower case
     * @param cityDto
     * @return
     */
    private boolean isStartsLowercase(CreateCityDto cityDto) {
        return cityDto.getName().matches("[a-z].+");
    }

}
