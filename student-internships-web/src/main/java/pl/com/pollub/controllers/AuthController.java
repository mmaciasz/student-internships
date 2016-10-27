package pl.com.pollub.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by mmaciasz on 2016-10-27.
 */
@RestController
public class AuthController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
