package com.heqing.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heqing
 * @date 2019/2/25 9:47
 */
@RestController
public class RibbonServiceBackupController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String ip;

    @GetMapping("/test")
    public String test() {
        String services = discoveryClient.getServices().toString();
        System.out.println("从 --> 服务器："+services);
        return "调用从服务器("+ip+")";
    }
}
