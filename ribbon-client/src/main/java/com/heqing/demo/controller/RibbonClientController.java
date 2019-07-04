package com.heqing.demo.controller;

import com.heqing.demo.UserTest;
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
public class RibbonClientController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    private String ip;

    @GetMapping("/test")
    public String test() {
        String services = restTemplate.getForObject("http://ribbo-service/test", String.class);
        return "调用服务器("+ip+")返回：[" + services + "]";
    }

    @GetMapping("/testGet")
    public String testGet() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "小黑");
        List<String> nameList = restTemplate.getForObject("http://ribbo-service/testGet?name={name}", List.class, params);
        return nameList.toString();
    }

    @GetMapping("/testPost")
    public String testPost() {
        UserTest userTest = new UserTest();
        userTest.setAge(29);
        userTest.setAddress("安庆");
        ResponseEntity<HashMap> result = restTemplate.postForEntity("http://ribbo-service/testPost", userTest, HashMap.class);
        Map<String, Object> userMap = result.getBody();
        return userMap.toString();
    }

    @GetMapping("/testPut")
    public void testPut() {
        restTemplate.put("http://ribbo-service/testPut", "");
    }

    @GetMapping("/testDelete")
    public void testDelete() {
        restTemplate.delete("http://ribbo-service/testDelete");
    }
}
