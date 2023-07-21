package ru.task4.hello.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.task4.hello.service.HelloService;

@RestController
@RequestMapping("api/v1/hello")
public class HelloController {
    private final HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping()
    public String sayHello(@RequestParam("name") String name){
        return helloService.sayHello() + ", " + name;
    }
}
