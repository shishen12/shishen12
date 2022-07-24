package com.qfedu.fmmall.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.fmmall.dao.IndexImgMapper;
import com.qfedu.fmmall.entity.IndexImg;
import com.qfedu.fmmall.entity.ProductImg;
import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.fmmall.service.IndexImgService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class IndexImgServiceImpl implements IndexImgService {

    @Autowired
    private IndexImgMapper indexImgMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public ResultVO listIndexImgs() {
        List<IndexImg> indexImgs=null;
        try {
            //String结构缓存轮播图信息
            //String indexImgs1 = stringRedisTemplate.opsForValue().get("indexImgs");
            String imgsStr = stringRedisTemplate.boundValueOps("indexImgs").get();
            if(imgsStr!=null){
                JavaType javaType1=objectMapper.getTypeFactory().constructParametricType(ArrayList.class, IndexImg.class);
                indexImgs=objectMapper.readValue(imgsStr,javaType1);
            }else{
                synchronized (this){
                    //第二次查询redis
                    String s=stringRedisTemplate.boundValueOps("indexImgs").get();
                    if(s==null){
                        //只有第一个请求再次查询redis时为null
                        indexImgs = indexImgMapper.listIndexImgs();
                        stringRedisTemplate.boundValueOps("indexImgs").set(objectMapper.writeValueAsString(indexImgs));
                        //设置过期时间为1天
                        stringRedisTemplate.boundValueOps("indexImgs").expire(1, TimeUnit.DAYS);
                    }else {
                        JavaType javaType1=objectMapper.getTypeFactory().constructParametricType(ArrayList.class, IndexImg.class);
                        indexImgs=objectMapper.readValue(s,javaType1);
                    }
                }

            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(indexImgs.size()==0){
            return new ResultVO(ResStatus.NO,"fail",null);
        }else {
            return new ResultVO(ResStatus.OK,"success",indexImgs);
        }
    }
}
