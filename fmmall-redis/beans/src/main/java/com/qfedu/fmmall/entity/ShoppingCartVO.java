package com.qfedu.fmmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 新增 productName productImg content
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartVO {

    private Integer cartId;
    private String productId;
    private String skuId;
    private String skuProps;
    private String userId;
    private String cartNum;
    private String cartTime;
    private BigDecimal productPrice;
    private String productName;
    private String content;
    private String productImg;
    private double originalPrice;
    private double sellPrice;
    private String skuName;
    private int skuStock;

}
