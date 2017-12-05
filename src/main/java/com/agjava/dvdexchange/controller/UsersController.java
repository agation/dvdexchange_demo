package com.agjava.dvdexchange.controller;

import com.agjava.dvdexchange.model.UserSignUpForm;
import com.agjava.dvdexchange.service.ISecurityService;
import com.agjava.dvdexchange.service.IUserService;
import com.agjava.dvdexchange.validator.UserSignUpValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@AllArgsConstructor
@Controller
public class UsersController {

    private UserSignUpValidator userValidator;

    private IUserService userService;

    private ISecurityService securityService;

    private LocaleResolver localeResolver;

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("signupForm", new UserSignUpForm());
        return "signup";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute("signupForm") UserSignUpForm signUpForm, BindingResult bindingResult) {
        this.userValidator.validate(signUpForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        this.userService.createNewUser(signUpForm);
        this.securityService.autoLogin(signUpForm.getName(), signUpForm.getPassword());

        return "redirect:/";
    }

    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public String signin(Model model, HttpServletRequest request) {
        model.addAttribute("langCode", this.localeResolver.resolveLocale(request).getLanguage());
        return "signin";
    }
}
