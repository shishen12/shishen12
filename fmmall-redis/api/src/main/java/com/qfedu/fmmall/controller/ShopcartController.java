package com.qfedu.fmmall.controller;

import com.auth0.jwt.impl.JWTParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.fmmall.entity.ShoppingCart;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.service.ShoppingCartService;
import com.qfedu.fmmall.utils.Base64Utils;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.jsonwebtoken.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopcart")
@CrossOrigin
@Api(value = "提供购物车业务相关接口",tags = "购物车管理")
public class ShopcartController {


    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private ObjectMapper objectMapper=new ObjectMapper();

    @ApiOperation("购物车添加接口")
    @PostMapping("/add")
    public ResultVO addShoppingCart(@RequestBody ShoppingCart cart,@RequestHeader("token")String token) throws JsonProcessingException {
        ResultVO resultVO=shoppingCartService.addShoppingCart(cart);
        String s = stringRedisTemplate.boundValueOps(token).get();
        Users users = objectMapper.readValue(s, Users.class);
        return resultVO;
    }

    @ApiOperation("购物车列表查询接口")
    @GetMapping("/list")
    @ApiImplicitParam(dataType = "int",name="userId",value = "用户ID",required = true)
    public ResultVO list(Integer userId,@RequestHeader("token")String token){
            ResultVO resultVO = shoppingCartService.listShoppingCartsByUserId(userId);
            return resultVO;
    }

    @ApiOperation("购物车商品数量更新接口")
    @PutMapping("/update/{cid}/{cnum}")
    public ResultVO updateNum(@PathVariable("cid") Integer cartId,
                              @PathVariable("cnum") Integer cartNum,
                              @RequestHeader("token")  String token){
        ResultVO resultVO = shoppingCartService.updateCartNum(cartId, cartNum);
        return  resultVO;
    }
    @ApiOperation("购物车订单删除接口")
    @DeleteMapping("delete/{cid}")
    public ResultVO deleteShopcart(@PathVariable("cid") Integer cartId,@RequestHeader("token")String token){
        ResultVO resultVO = shoppingCartService.deleteShoppingCartByCartId(cartId);
        return resultVO;
    }

    @ApiOperation("购物车提交订单接口")
    @GetMapping("/listbycids")
    @ApiImplicitParam(dataType = "String",name="cids",value = "购物车ID",required = true)
    public ResultVO listByCids(String cids, @RequestHeader("token")String token){
        ResultVO resultVO = shoppingCartService.listShoppingCartsByCids(cids);
        return resultVO;
    }

}

