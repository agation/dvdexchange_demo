package com.agjava.dvdexchange.validator;

import com.agjava.dvdexchange.model.DiskForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DiskFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return DiskForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DiskForm diskForm = (DiskForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.disk.add.name.required.characters");

        if (diskForm.getName().length() < 2 || diskForm.getName().length() > 200) {
            errors.rejectValue("name", "error.disk.add.name.required.size");
        }
    }
}
