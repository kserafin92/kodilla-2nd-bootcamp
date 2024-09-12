package com.kodilla.bytebuddy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class JavaAgentController {

    @GetMapping("/doSomething")
    public String doSomething() {
        User user = new User();
        user.doSomething();
        return "Method doSomething() was called.";
    }

}
