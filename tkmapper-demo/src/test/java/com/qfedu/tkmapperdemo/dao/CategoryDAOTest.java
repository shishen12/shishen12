package com.qfedu.tkmapperdemo.dao;

import com.qfedu.tkmapperdemo.TkmapperDemoApplication;
import com.qfedu.tkmapperdemo.beans.Category;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TkmapperDemoApplication.class)
public class CategoryDAOTest {

    @Resource
    private CategoryDAO categoryDAO;

    @Test
    public void testInsert(){
        Category category=new Category(0,"测试类别1",1,0,"01.png","hehe","aa.jpg","black");

       int i = categoryDAO.insert(category);
       assertEquals(1,i);
    }

    @Test
    public void testUpdate(){
        Category category=new Category(1,"测试类别4",2,0,"02.png","hesaashe","aa.jpg","black");

        int i = categoryDAO.updateByPrimaryKey(category);
        assertEquals(1,i);

    }
    @Test
    public void testDelete(){
        Category category=new Category(1,"测试类别4",2,0,"02.png","hesaashe","aa.jpg","black");

        int i = categoryDAO.deleteByPrimaryKey(1);
        assertEquals(1,i);

    }


    @Test
    public void testSelect1(){
        List<Category> categories = categoryDAO.selectAll();
        for(Category category:categories){
            System.out.println(category);
        }
    }
    @Test
    public void testSelect2(){
        Category category=categoryDAO.selectByPrimaryKey(1);
    }
    @Test
    public void testSelect3(){
        Example example =new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryLevel",1);
        List<Category> categories = categoryDAO.selectByExample(example);

        for(Category category:categories){
            System.out.println(category);
        }

    }
    @Test
    public void testSelect4(){
        int pageNum=2;
        int pageSize=10;
        int start=(pageNum-1)*pageSize;
        RowBounds rowBounds=new RowBounds(start,pageSize);
        List<Category> categories = categoryDAO.selectByRowBounds(new Category(), rowBounds);
        for(Category category:categories){
            System.out.println(category);
        }
        int i = categoryDAO.selectCount(new Category());
        System.out.println(i);

    }
    @Test
    public void testSelect5(){
        Example example =new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryLevel",1);
        int pageNum=1;
        int pageSize=3;
        int start=(pageNum-1)*pageSize;
        RowBounds rowBounds=new RowBounds(start,pageSize);
        List<Category> categories = categoryDAO.selectByExampleAndRowBounds(example, rowBounds);
        for(Category category:categories){
            System.out.println(category);
        }
        int i = categoryDAO.selectCountByExample(example);
        System.out.println(i);
    }
}
