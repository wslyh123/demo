package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class HelloController {

    static Logger logger = Logger.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${author.name}")
    private String name;
    @Value("${author.qq}")
    private String qq;

    @RequestMapping(value = "/kafka/{str}", method = RequestMethod.GET)
    public String kafka(@PathVariable(required = false) String str) {
        kafkaTemplate.send("test1",str);
        return str;
    }
    @RequestMapping(value = "/redis/{str}", method = RequestMethod.GET)
    public String redisTest (@PathVariable(required = false) String str) {
        stringRedisTemplate.opsForValue().set("demo",str);
        return "success: demo is " + str;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index () {
//        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.isHidden();
//            }
//        });
//        hiddenFiles = new File(".").listFiles(File :: isHidden);
//        List<String> list = new ArrayList<>();
//        list.forEach(System.out::println);
        ServiceInstance instance = client.getLocalServiceInstance();
        int sleepTime = new Random().nextInt(3000);
        logger.info("Now the thread will sleep :" + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = instance.getHost();
        if (name == null) {
            name = "hhh: I am null!";
        }
        logger.info("/hello, host:"+instance.getHost()+", service_id:"+instance.getServiceId());
        return "Hello World! The author is " + this.name + " and qq is " + qq + "." + name;
    }
    @KafkaListener(topics = {"test1"})
    public void ConsumerListener(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info(cr.toString());
    }
}
