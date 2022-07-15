package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.ProductCommentsMapper;
import com.qfedu.fmmall.dao.UserAddrMapper;
import com.qfedu.fmmall.dao.UsersMapper;
import com.qfedu.fmmall.entity.ProductComments;
import com.qfedu.fmmall.entity.ProductCommentsVO;
import com.qfedu.fmmall.entity.UserAddr;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.service.UserAddrService;
import com.qfedu.fmmall.service.UserService;
import com.qfedu.fmmall.utils.Base64Utils;
import com.qfedu.fmmall.utils.MD5Utils;
import com.qfedu.fmmall.utils.PageHelper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Scope("singleton")
public class UserServiceImpl implements UserService {


    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private ProductCommentsMapper productCommentsMapper;

    @Transactional
    public ResultVO userRegist(String name, String pwd) {

        synchronized (this){
            //根据用户名查询用户名是否已经注册
            Example example = new Example(Users.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username",name);
            List<Users> users = usersMapper.selectByExample(example);
            //如果没有注册则进行保存
            if(users.size()==0){
                String md5Pwd = MD5Utils.string2MD5(pwd);
                Users user=new Users();
                user.setUsername(name);
                user.setPassword(md5Pwd);
                user.setUserImg("img/default.png");
                user.setUserRegtime(new Date());
                user.setUserModtime(new Date());
                int i=usersMapper.insertUseGeneratedKeys(user);
                if(i>0){
                    return new ResultVO(ResStatus.OK,"注册成功！",user);
                }else{
                    return new ResultVO(ResStatus.NO,"注册失败！",null);
                }

            }else{
                return new ResultVO(ResStatus.NO,"用户名已经被注册！",null);
            }
        }
    }

    @Override
    public ResultVO checkLogin(String name, String pwd) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",name);
        List<Users> users = usersMapper.selectByExample(example);
        if(users.size()==0){
            return new ResultVO(ResStatus.NO,"登录失败，用户名不存在！",null);
        }else{
            String md5Pwd = MD5Utils.string2MD5(pwd);
            if(md5Pwd.equals(users.get(0).getPassword())){
                JwtBuilder builder = Jwts.builder();
                HashMap<String,Object> map=new HashMap<>();
                map.put("key1","value1");
                map.put("key2","value2");

                String token = builder.setSubject(name)//token携带的数据
                        .setIssuedAt(new Date())//设置token生成时间
                        .setId(users.get(0).getUserId() + "")//设置用户tokenid
                        .setClaims(map)//存放用户权限信息
                        .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))//设置token过期时间
                        .signWith(SignatureAlgorithm.HS256, "ZHUzhu8023")//设置加密方式和加密密码
                        .compact();
                return new ResultVO(ResStatus.OK,token,users.get(0));
            }else{
                return new ResultVO(ResStatus.NO,"登陆失败，密码错误！",null);
            }
        }

    }

    @Override
    public ResultVO userInfo(int userId) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        List<Users> users = usersMapper.selectByExample(example);
        if(users.size()>0){
            return new ResultVO(ResStatus.OK,"success",users);
        }else{
            return new ResultVO(ResStatus.NO,"fail",null);
        }


    }

    @Override
    public int userUpdate(Users user) {
        int i=usersMapper.updateByPrimaryKeySelective(user);
        return i;
    }

    public ResultVO userComment(int userId, int pageNum, int limit) {
        //根据商品id查询总记录数
        Example example=new Example(ProductComments.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("userId",userId);
        int count=productCommentsMapper.selectCountByExample(example);

        //2.计算总页数(pageSize=limit)
        int pageCount=count%limit==0?count/limit:count/limit+1;

        //3.查询当前页的数据(评论需要用户信息,因此需要连表查询---自定义)
        int start=(pageNum-1)*limit;
        List<ProductCommentsVO> list = productCommentsMapper.selectCommontsByuserId(userId, start, limit);
        return new ResultVO(ResStatus.OK,"success",new PageHelper<ProductCommentsVO>(count,pageCount,list));
    }

}
