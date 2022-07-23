package com.qfedu.redis.service;

import com.qfedu.redis.beans.Product;
import org.springframework.stereotype.Service;


public interface ProductService {
    public void addInfoToRedis();
    public Product getInfoFromRedis(String productId);
}
