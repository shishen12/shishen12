package com.qfedu.redis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.redis.beans.Product;
import com.qfedu.redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

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

    @Override
    public Product getInfoFromRedis(String productId) {
        Product product=null;
        try {
            String s = stringRedisTemplate.boundValueOps(productId).get();
            if(s!=null){
                product = new ObjectMapper().readValue(s, Product.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return product;
    }
}
