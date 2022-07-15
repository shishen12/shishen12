package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.ProductCommentsVO;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper extends GeneralDAO<Users> {

}
