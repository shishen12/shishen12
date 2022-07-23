package com.qfedu.fmmall;

import com.qfedu.fmmall.dao.*;
import com.qfedu.fmmall.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =ApiApplication.class)
class ApiApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Test
    public void testRecommand(){
        List<ProductVO> productVOS=productMapper.selectRecommendProducts();
        for(ProductVO p:productVOS){
            System.out.println(p);
        }
    }

    @Test
    void contextLoads() {
        List<CategoryVO> categoryVOS = categoryMapper.selectAllCategories2(0);
        for(CategoryVO c1:categoryVOS){
            System.out.println(c1);
            for(CategoryVO c2:c1.getCategories()){
                System.out.println("\t"+c2);
                for(CategoryVO c3:c2.getCategories()){
                    System.out.println("\t"+c3);

                }
            }
        }
    }


    @Test
    public void testSelectFirstLevelCategory(){
        List<CategoryVO> categoryVOS=categoryMapper.selectAllCategories();
        for(CategoryVO c:categoryVOS){
            System.out.println(c);
        }
    }


    @Test
    public void testshopcart(){
        List<Integer> cids=new ArrayList<>();
        cids.add(26);
        cids.add(27);
        List<ShoppingCartVO> shoppingCartVOS=shoppingCartMapper.selectShopcartByCids(cids);
        System.out.println(shoppingCartVOS);
    }

}
