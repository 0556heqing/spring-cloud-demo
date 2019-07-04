package com.heqing.demo.controller;

import com.heqing.demo.UserTest;
import com.heqing.demo.service.FeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heqing
 * @date 2019/2/25 9:57
 */
@RestController
public class FeignClientController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FeignClientService feignClientService;

    @Value("${server.port}")
    private String ip;

    @GetMapping("/test")
    public String test() {
        String services = restTemplate.getForObject("http://feign-service/test", String.class);
        return "调用服务器("+ip+")返回：[" + services + "]";
    }

    @GetMapping("/test1")
    public String test1() {
        String services = feignClientService.test();
        return "调用服务器("+ip+")返回：[" + services + "]";
    }

    @GetMapping("/testGet")
    public String testGet() {
        List<String> nameList = feignClientService.testGet("小黑");
        return nameList.toString();
    }

    @GetMapping("/testPost")
    public String testPost() {
        UserTest userTest = new UserTest();
        userTest.setAge(29);
        userTest.setAddress("安庆");
        Map<String, Object> result = feignClientService.testPost(userTest);
        return result.toString();
    }

    @GetMapping("/testPut")
    public String testPut() {
        return feignClientService.testPut();
    }

    @GetMapping("/testDelete")
    public String testDelete() {
        return feignClientService.testDelete();
    }
}
