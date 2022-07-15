package com.qfedu.utils;

import com.qfedu.dao.MemberDAO;
import com.qfedu.pojo.Member;

import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        MemberDAO memberDAO=MyBatisUtil.getMapper(MemberDAO.class);
        HashMap<String, Object> params = new HashMap();
        params.put("keyWord","花");
        List<Member> members=memberDAO.searchMemberByNick(params);
        for (Member m:members) {
            System.out.println(m);
        }
    }
}
