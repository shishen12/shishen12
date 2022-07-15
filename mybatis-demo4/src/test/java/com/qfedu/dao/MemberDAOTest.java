package com.qfedu.dao;

import com.qfedu.pojo.Member;
import com.qfedu.utils.MyBatisUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class MemberDAOTest {
    @Test
    public void searchMember() {
        HashMap<String, Object> params = new HashMap();
        params.put("gender", "女");
        MemberDAO memberDAO = MyBatisUtil.getMapper(MemberDAO.class);
        List<Member> members = memberDAO.searchMember(params);


        for (Member m:members) {
            System.out.println(m);
        }
    }

    @Test
    public void searchMemberByCity() {

        List<String> cities=new ArrayList<>();
        cities.add("武汉");
        cities.add("宜昌");
        MemberDAO memberDAO=MyBatisUtil.getMapper(MemberDAO.class);
        List<Member> members=memberDAO.searchMemberByCity(cities);
        for (Member m:members) {
            System.out.println(m);
        }
    }

    @Test
    public void testSearchMemberByNick(){
        MemberDAO memberDAO=MyBatisUtil.getMapper(MemberDAO.class);
        HashMap<String, Object> params = new HashMap();
        params.put("keyWord","花");
        List<Member> members=memberDAO.searchMemberByNick(params);
        for (Member m:members) {
            System.out.println(m);
        }
    }
}