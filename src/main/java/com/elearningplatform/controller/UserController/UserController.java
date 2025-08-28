package com.elearningplatform.controller.UserController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {


    @GetMapping()
    public String wakeUp(){
        return("Hello World");
    }

}
