package com.heqing.demo.service.fallback;

import com.heqing.demo.UserTest;
import com.heqing.demo.service.FeignClientService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heqing
 * @date 2019/7/2 14:41
 */
@Component
public class FeignClientServiceFallBack implements FeignClientService {

    @Override
    public String test() {
        return "error";
    }

    @Override
    public List<String> testGet(String name) {
        List<String> list = new ArrayList<>();
        list.add("error");
        return list;
    }

    @Override
    public Map<String, Object> testPost(UserTest userTest) {
        return new HashMap<>();
    }

    @Override
    public String testPut() {
        return "";
    }

    @Override
    public String testDelete() {
        return "";
    }
}
