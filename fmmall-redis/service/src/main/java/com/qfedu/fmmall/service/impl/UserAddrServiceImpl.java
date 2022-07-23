package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.UserAddrMapper;
import com.qfedu.fmmall.entity.UserAddr;
import com.qfedu.fmmall.service.UserAddrService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UserAddrServiceImpl implements UserAddrService {

    @Autowired
    private UserAddrMapper userAddrMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO listAddrsByUid(int userId) {
        Example example = new Example(UserAddr.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("status",1);
        List<UserAddr> userAddrs=userAddrMapper.selectByExample(example);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", userAddrs);
        return resultVO;
    }

    @Override
    public int userInsertAddr(UserAddr userAddr) {
        userAddr.setCreateTime(new Date());
        userAddr.setUpdateTime(new Date());
        int i= userAddrMapper.insertUseGeneratedKeys(userAddr);
        return i;
    }

    @Override
    public int userUpdateAddr(UserAddr userAddr) {
        userAddr.setUpdateTime(new Date());
        int i=userAddrMapper.updateByPrimaryKeySelective(userAddr);
        return i;
    }

    @Override
    public int userDeleteAddr(int addrId) {
        int i = userAddrMapper.deleteByPrimaryKey(addrId);
        return i;
    }

    @Override
    public int setDefaultAddr(String userId, int addrId) {
        int i = userAddrMapper.setDefaultNull(userId);
        int j = userAddrMapper.setDefaultAddr(addrId);
        return j;

    }

}
