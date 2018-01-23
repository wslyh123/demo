package com.example.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    com.example.customer.HelloService helloService;

    @RequestMapping(value = "/customer",method = RequestMethod.GET)
    public String HelloCustomer() {
        return "The customer has get the response:" + helloService.helloService();
    }
}
