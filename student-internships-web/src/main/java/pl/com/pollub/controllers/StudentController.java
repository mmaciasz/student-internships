package pl.com.pollub.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mateusz on 16.10.16.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(){
        return "<html><body><div><h1>Hello!</h1></div></body></html>";
    }
}
