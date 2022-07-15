package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.UserAddr;
import com.qfedu.fmmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddrMapper extends GeneralDAO<UserAddr> {
    public int setDefaultNull(String userId);
    public int setDefaultAddr(int addrId);

}
