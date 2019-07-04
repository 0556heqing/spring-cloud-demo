package com.heqing.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heqing
 * @date 2019/2/25 9:57
 */
@RefreshScope
@RestController
public class ConfigClientController {

    @Value("${env}")
    private String env;

    @GetMapping("/test")
    public String test() {
        return "-->" + this.env;
    }

}
