package com.qfedu.redis.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private  String productId;
    private  String productName;
    private  Double productPrice;
}
