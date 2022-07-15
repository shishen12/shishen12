package com.qfedu.dao;

import com.qfedu.pojo.Member;
import com.qfedu.utils.MyBatisUtil;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class MemberDAOTest {

    public void searchMember() {
        HashMap<String,Object> params=new HashMap<String, Object>();
        params.put("gender", "女");
//        params.put("minAge",18);
//        params.put("city","武汉");

        MemberDAO memberDAO= MyBatisUtil.getMapper(MemberDAO.class);
        List<Member> members=memberDAO.searchMember(params);
        System.out.println(members);
    }
}