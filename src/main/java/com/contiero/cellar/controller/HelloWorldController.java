package com.contiero.cellar.controller;

// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping("/hi")
    public ResponseEntity<String> index() {
        return new ResponseEntity<String>("Hello user!", HttpStatus.OK);
    }

}
