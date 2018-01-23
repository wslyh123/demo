package com.example.customer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.apache.log4j.Logger;

@Service
public class HelloService {

    static Logger logger = Logger.getLogger(HelloService.class);

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService() {
        long start = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://HELLO-SERVICE/hello",String.class);
        long end = System.currentTimeMillis();
        logger.info("SpendTime:" + (end - start));
        return result;
    }

    public String helloFallback() {
        return "error";
    }
}
