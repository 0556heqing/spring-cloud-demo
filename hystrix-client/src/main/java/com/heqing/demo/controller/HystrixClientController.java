package com.heqing.demo.controller;

import com.heqing.demo.service.HystrixClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heqing
 * @date 2019/2/25 9:57
 */
@RestController
public class HystrixClientController {

    @Autowired
    HystrixClientService hystrixClientService;

    @GetMapping("/test")
    public String test() {
        return hystrixClientService.test();
    }

}
