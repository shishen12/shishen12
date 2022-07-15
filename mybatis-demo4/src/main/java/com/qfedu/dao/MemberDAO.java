package com.qfedu.dao;


import com.qfedu.pojo.Member;
import java.util.HashMap;
import java.util.List;


public interface MemberDAO {

    public List<Member> searchMember(HashMap<String, Object> params);






    public List<Member> searchMemberByCity(List<String> cities);

    public List<Member> searchMemberByNick(HashMap params);

}
