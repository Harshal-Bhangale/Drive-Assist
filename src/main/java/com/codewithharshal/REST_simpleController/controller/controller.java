package com.codewithharshal.REST_simpleController.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class controller {

    @GetMapping("/hello/{name}/{age}")
    public String sayHello(@PathVariable String name, @PathVariable int age)
    {
        return "Hello, " + name + "! Your are " + age +" years old.";
    }

    @PostMapping("/")
    public String addHello(){
        return "Add, Your Info ";
    }


}
