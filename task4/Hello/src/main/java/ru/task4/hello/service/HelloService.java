package ru.task4.hello.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String sayHello(){
        return "Hello";
    }
}
