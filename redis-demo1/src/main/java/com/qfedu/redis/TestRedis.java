package com.qfedu.redis;

import com.google.gson.Gson;
import com.qfedu.beans.Product;
import redis.clients.jedis.Jedis;

public class TestRedis {
    public static void main(String[] args) {
        Product product = new Product("101", "哇哈哈", 2.5);

        //1.连接redis
        Jedis jedis = new Jedis("43.142.17.246", 6379);
        jedis.auth("admin123");
        String s = jedis.set(product.getProductId(), new Gson().toJson(product));
        System.out.println(s);
        jedis.close();
    }
}
