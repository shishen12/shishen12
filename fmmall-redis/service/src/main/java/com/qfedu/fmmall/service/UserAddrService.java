package com.qfedu.fmmall.service;

import com.qfedu.fmmall.entity.UserAddr;
import com.qfedu.fmmall.vo.ResultVO;

public interface UserAddrService {
    public ResultVO listAddrsByUid(int userId);
    //用户地址添加
    public int userInsertAddr(UserAddr userAddr);
    //用户地址修改
    public int userUpdateAddr(UserAddr userAddr);
    //用户地址删除
    public int userDeleteAddr(int addrId);
    //设置默认地址
    public int setDefaultAddr(String userId,int addrId);
}
