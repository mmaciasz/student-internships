package pl.com.pollub.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by mmaciasz on 2016-10-27.
 */
@RestController
public class AuthController implements ErrorController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/error")
    public ModelAndView error() {
        return new ModelAndView("redirect:/#/error");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
