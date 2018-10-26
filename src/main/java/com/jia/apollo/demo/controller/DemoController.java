package com.jia.apollo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述：
 *
 * @author LuJia
 * @date 2018/10/11 17:24
 */
@RestController
public class DemoController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Value("${name:'adjia'}")
    private String name;

    @GetMapping("/hello")
    public String hello() {
        return name;
    }

    @GetMapping("/get")
    public String get(String key) {
        BoundValueOperations<String, String> operations = stringRedisTemplate.boundValueOps(key);
        return operations.get();
    }

    @GetMapping("/set")
    public HttpStatus set(String key, String value) {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set(key, value);
        return HttpStatus.OK;
    }

}
