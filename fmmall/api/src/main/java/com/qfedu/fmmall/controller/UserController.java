package com.qfedu.fmmall.controller;


import com.qfedu.fmmall.entity.ProductComments;
import com.qfedu.fmmall.entity.UserAddr;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.service.ProductCommontsService;
import com.qfedu.fmmall.service.UserService;
import com.qfedu.fmmall.utils.Base64Utils;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(value = "提供用户的登录和注册接口",tags = "用户管理")
//允许跨域
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private ProductCommontsService productCommontsService;

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username",value = "用户登录账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "password",value = "用户登录密码",required = true,defaultValue = "111111")

    })
    @GetMapping("/login")
    public ResultVO login(@RequestParam("username") String name,
                          @RequestParam(value = "password") String pwd){
        ResultVO resultVO = userService.checkLogin(name, pwd);
        return  resultVO;
    }

    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username",value = "用户注册账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "password",value = "用户注册密码",required = true,defaultValue = "111111")

    })
    @PostMapping("/regist")
    public ResultVO regist(@RequestBody Users user){
        ResultVO resultVO = userService.userRegist(user.getUsername(),user.getPassword());
        return resultVO;
    }
    @ApiOperation("校验token是否过期")
    @GetMapping("/check")
    public ResultVO userTokencheck(@RequestHeader("token")String token){
        return new ResultVO(ResStatus.OK,"success",null);
    }

    @ApiOperation("用户信息查询接口")
    @GetMapping("/list")
    @ApiImplicitParam(dataType = "int",name="userId",value = "用户ID",required = true)
    public ResultVO list(Integer userId,@RequestHeader("token")String token){
        ResultVO resultVO =userService.userInfo(userId);
        return resultVO;
    }

    @ApiOperation("用户信息修改接口")
    @PostMapping("/update")
    public ResultVO updateUser(@RequestBody Users user,@RequestHeader("token")  String token){
        int i=userService.userUpdate(user);
        if(i==1){
            return new ResultVO(ResStatus.OK,"success",null);
        }
        else {
            return new ResultVO(ResStatus.NO,"fail",null);
        }
    }

    @ApiOperation("用户评论信息查询接口")
    @GetMapping("/commonts/{uid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageNum",value = "当前页面",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit",value = "每页显示条数",required = true)

    })
    public ResultVO getProductCommonts(@PathVariable("uid") int uid,int pageNum,int limit,@RequestHeader("token")  String token){
        return userService.userComment(uid,pageNum,limit);
    }

    @ApiOperation("用户评论提交查询接口")
    @PostMapping("/submitCommonts")
    public ResultVO submitCommonts(@RequestBody ProductComments productComments, @RequestHeader("token")  String token){
        int i = productCommontsService.submitComment(productComments);
        if(i==1){
            return new ResultVO(ResStatus.OK,"success",null);
        }
        else {
            return new ResultVO(ResStatus.NO,"fail",null);
        }
    }
}
