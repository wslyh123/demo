package com.demo.Groovy

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class App {

    @RequestMapping("/")
    String home(@RequestParam String name) {
        "Hello World!" + name
    }

}