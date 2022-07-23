package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.entity.UserAddr;
import com.qfedu.fmmall.service.UserAddrService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(value = "提供收货地址相关接口",tags = "收货地址管理")
@RequestMapping("/useraddr")
public class UserAddrController {

    @Autowired
    private UserAddrService userAddrService;

    @ApiOperation("用户地址查询接口")
    @GetMapping("/list")
    @ApiImplicitParam(dataType = "int",name="userId",value = "用户ID",required = true)
    public ResultVO listAddr(Integer userId, @RequestHeader("token")String token){
        ResultVO resultVO = userAddrService.listAddrsByUid(userId);
        return resultVO;
    }

    @ApiOperation("用户地址添加接口")
    @PostMapping("/insertAddr")
    public ResultVO insertAddress(@RequestBody UserAddr userAddr, @RequestHeader("token")  String token){
        int i =userAddrService.userInsertAddr(userAddr);
        if(i==1){
            return new ResultVO(ResStatus.OK,"success",null);
        }
        else {
            return new ResultVO(ResStatus.NO,"fail",null);
        }
    }
    @ApiOperation("用户地址修改接口")
    @PostMapping("/updateAddr")
    public ResultVO updateAddress(@RequestBody UserAddr userAddr, @RequestHeader("token")  String token){
        int i =userAddrService.userUpdateAddr(userAddr);
        if(i==1){
            return new ResultVO(ResStatus.OK,"success",null);
        }
        else {
            return new ResultVO(ResStatus.NO,"fail",null);
        }
    }

    @ApiOperation("用户地址删除接口")
    @DeleteMapping("deleteAddr/{addrId}")
    public ResultVO deleteShopcart(@PathVariable("addrId") Integer addrId,@RequestHeader("token")String token){
        int i =userAddrService.userDeleteAddr(addrId);
        if(i==1){
            return new ResultVO(ResStatus.OK,"success",null);
        }
        else {
            return new ResultVO(ResStatus.NO,"fail",null);
        }
    }

    @ApiOperation("设置用户默认地址接口")
    @PutMapping("setDefaultAddr")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name="userId",value = "用户ID",required = true),
            @ApiImplicitParam(dataType = "int",name="addrId",value = "地址ID",required = true)
    })
    public ResultVO setDefaultAddr(String userId,Integer addrId,@RequestHeader("token")String token){
        int i =userAddrService.setDefaultAddr(userId,addrId);
        if(i==1){
            return new ResultVO(ResStatus.OK,"success",null);
        }
        else {
            return new ResultVO(ResStatus.NO,"fail",null);
        }
    }

}
