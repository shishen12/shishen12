package com.qfedu.fmmall.service;

import com.qfedu.fmmall.entity.UserAddr;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.vo.ResultVO;

public interface UserService {

    //用户注册
    public ResultVO userRegist(String name, String pwd);
    //用户登录
    public ResultVO checkLogin(String name, String pwd);
    //用户信息查询
    public ResultVO userInfo(int userId);
    //用户信息修改
    public int userUpdate(Users user);
    //用户评论查询
    public ResultVO userComment(int userId,int pageNum,int limit);
}
