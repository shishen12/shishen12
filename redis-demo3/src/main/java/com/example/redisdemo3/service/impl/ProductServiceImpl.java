package com.example.redisdemo3.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.redis.beans.Product;
import com.qfedu.redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


public class ProductServiceImpl implements ProductService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInfoToRedis() {
        try {
            Product product = new Product("103", "王老吉", 4.0);
            String jsonStr = new ObjectMapper().writeValueAsString(product);
            stringRedisTemplate.boundValueOps(product.getProductId()).set(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
