package com.heqing.demo.controller;

import com.heqing.demo.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heqing
 * @date 2019/2/25 9:47
 */
@RestController
public class RibbonServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String ip;

    @GetMapping("/test")
    public String test() {
        String services = discoveryClient.getServices().toString();
        System.out.println("主 --> 服务器："+services);
        return "调用主服务器("+ip+")";
    }

    @GetMapping("/testGet")
    public List<String> testGet(String name) {
        List<String> nameList = new ArrayList<>();
        nameList.add("小白");
        nameList.add(name);
        return nameList;
    }

    @PostMapping("/testPost")
    public Map<String, Object> testPost(@RequestBody UserTest userTest) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", "小白");
        if(userTest != null) {
            userMap.put("age", userTest.getAge());
            userMap.put("address", userTest.getAddress());
        }
        return userMap;
    }

    @PutMapping("/testPut")
    public void testPut() {
        System.out.println("--> put请求");
    }

    @DeleteMapping("/testDelete")
    public void testDelete() {
        System.out.println("--> delete请求");
    }
}
