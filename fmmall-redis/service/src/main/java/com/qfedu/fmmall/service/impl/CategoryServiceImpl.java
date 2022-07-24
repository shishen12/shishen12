package com.qfedu.fmmall.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.fmmall.dao.CategoryMapper;
import com.qfedu.fmmall.entity.CategoryVO;
import com.qfedu.fmmall.entity.IndexImg;
import com.qfedu.fmmall.service.CategoryService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public ResultVO listCategories() {
        List<CategoryVO> categoryVOS=null;
        try {
            //查询redis
            String cateories = stringRedisTemplate.boundValueOps("cateories").get();
            if(cateories!=null){
                JavaType javaType1=objectMapper.getTypeFactory().constructParametricType(ArrayList.class,CategoryVO.class);
                categoryVOS=objectMapper.readValue(cateories,javaType1);
            }else {
                categoryVOS= categoryMapper.selectAllCategories();
                stringRedisTemplate.boundValueOps("cateories").set(objectMapper.writeValueAsString(categoryVOS),1, TimeUnit.DAYS);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", categoryVOS);
        return resultVO;

    }

    @Override
    public ResultVO listFirstLevelCategories() {
        List<CategoryVO> categoryVOS = categoryMapper.selectFirstLevelCategories();
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", categoryVOS);
        return resultVO;
    }
}
