package com.agjava.dvdexchange.validator;

import com.agjava.dvdexchange.model.UserSignUpForm;
import com.agjava.dvdexchange.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Slf4j
@AllArgsConstructor
@Component
public class UserSignUpValidator implements Validator {

    private IUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserSignUpForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserSignUpForm userForm = (UserSignUpForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.signup.username.required.characters");
        if (userForm.getName().contains(" ")) {
            errors.rejectValue("name", "error.signup.username.required.characters");
        }

        if (userForm.getName().length() < 3 || userForm.getName().length() > 20) {
            errors.rejectValue("name", "error.signup.username.required.size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.signup.password.required.characters");
        if (userForm.getPassword().length() < 3 || userForm.getPassword().length() > 15) {
            errors.rejectValue("password", "error.signup.password.required.size");
        }

        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "error.signup.password.not.confirm");
        }

        if (userService.findByUserName(userForm.getName()) != null) {
            errors.rejectValue("name", "error.signup.username.exist");
        }
    }
}
