package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController //Controller+Responsebody
@RequestMapping("/goods")
@Api(value = "提供商品添加，修改，删除及查询相关接口",tags = "商品管理")
public class GoodsController {

    //增
    @PostMapping("/add")
    public ResultVO addGoods(){ return null; }

    //删
    @DeleteMapping("/{id}")
    public ResultVO deleteGoods(@PathVariable("id") int goodsId){
        System.out.println("-----"+goodsId);
        return new ResultVO(10000,"delete success",null);
    }

    //改
    @PutMapping("/{id}")
    public ResultVO updateGoods(/*Goods goods*/){ return  null; }

    //查
    @GetMapping("/list")
    public ResultVO listGoods(){ return  null; }


}

