package com.example.interviewskeleton.controller;

import com.example.interviewskeleton.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greet/{name}")
    //The controller returns the greeting message in the needed language, but also greets the user based on the locale time zone (extra-work). :)
    public ResponseEntity<String> greet(@PathVariable String name, @RequestParam("locale") String locale) {

        String message = greetingService.greet(name, locale);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }
}
