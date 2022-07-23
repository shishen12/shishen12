package com.example.redisdemo3.beans;


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
