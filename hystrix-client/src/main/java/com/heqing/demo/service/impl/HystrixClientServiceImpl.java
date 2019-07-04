package com.heqing.demo.service.impl;

import com.heqing.demo.service.HystrixClientService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author heqing
 * @date 2019/7/1 15:29
 */
@Service
public class HystrixClientServiceImpl implements HystrixClientService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    private String ip;

    @Override
    @HystrixCommand(fallbackMethod = "error")
    public String test() {
        String services = restTemplate.getForObject("http://hystrix-service/test", String.class);
        return "调用服务器("+ip+")返回：[" + services + "]";
    }

    public String error() {
        return "error";
    }
}
