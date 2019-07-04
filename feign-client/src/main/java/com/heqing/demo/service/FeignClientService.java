package com.heqing.demo.service;

import com.heqing.demo.UserTest;
import com.heqing.demo.service.fallback.FeignClientServiceFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author heqing
 * @date 2019/7/2 10:04
 */
@FeignClient(name="feign-service", fallback=FeignClientServiceFallBack.class)
public interface FeignClientService {

    @RequestMapping(value="/test", method=RequestMethod.GET)
    String test();

    @RequestMapping(value="/testGet", method=RequestMethod.GET)
    List<String> testGet(@RequestParam("name") String name);

    @RequestMapping(value="/testPost", method=RequestMethod.POST)
    Map<String, Object> testPost(@RequestBody UserTest userTest);

    @RequestMapping(value="/testPut", method=RequestMethod.PUT)
    String testPut();

    @RequestMapping(value="/testDelete", method=RequestMethod.DELETE)
    String testDelete();
}
