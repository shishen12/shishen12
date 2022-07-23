package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.ProductImgMapper;
import com.qfedu.fmmall.dao.ProductMapper;
import com.qfedu.fmmall.dao.ProductParamsMapper;
import com.qfedu.fmmall.dao.ProductSkuMapper;
import com.qfedu.fmmall.entity.*;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.utils.PageHelper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImgMapper productImgMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductParamsMapper productParamsMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultVO listRecommendProducts() {
        List<ProductVO> productVOS = productMapper.selectRecommendProducts();
        ResultVO resultVO=new ResultVO(ResStatus.OK,"success",productVOS);
        return resultVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO getProductBasicInfo(String productId) {
        //商品基本信息
        Example example = new Example(Product.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("productId",productId);
        criteria.andEqualTo("productStatus",1);
        List<Product> products=productMapper.selectByExample(example);
        if(products.size()>0){
            //商品图片
            Example example1=new Example(ProductImg.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("itemId",productId);
            List<ProductImg> productImgs = productImgMapper.selectByExample(example1);

            //商品套餐
            Example example2 = new Example(ProductSku.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("productId",productId);
            criteria2.andEqualTo("status",1);
            List<ProductSku> productSkus = productSkuMapper.selectByExample(example2);

            HashMap<String,Object> basicInfo=new HashMap<>();
            basicInfo.put("product",products.get(0));
            basicInfo.put("productImgs",productImgs);
            basicInfo.put("productSkus",productSkus);
            return new ResultVO(ResStatus.OK,"success",basicInfo);
        }else {
            return new ResultVO(ResStatus.NO,"查询商品不存在",null);
        }
    }

    @Override
    public ResultVO getProductparamsById(String productId) {
        Example example=new Example(ProductParams.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("productId",productId);
        List<ProductParams> productParams=productParamsMapper.selectByExample(example);
        if(productParams.size()>0){
            return new ResultVO(ResStatus.OK,"success",productParams.get(0));
        }else{
            return new ResultVO(ResStatus.NO,"三无产品",null);
        }
    }

    @Override
    public ResultVO getProductsByCategoryId(int categoryId, int pageNum, int limit) {

        int start=(pageNum-1)*limit;
        List<ProductVO> productVOS = productMapper.selectProductByCategoryId(categoryId, start, limit);

        Example example = new Example(Product.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("categoryId",categoryId);
        int count=productMapper.selectCountByExample(example);
        int pageCount=count%limit==0? count/limit :count/limit+1;
        PageHelper<ProductVO> pageHelper = new PageHelper<>(count, pageCount, productVOS);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", pageHelper);
        return resultVO;
    }

    @Override
    public ResultVO listBrands(int catagoryId) {
        List<String> brands = productMapper.selectBrandByCategoryId(catagoryId);
        return new ResultVO(ResStatus.OK,"success",brands);
    }

    @Override
    public ResultVO searchProduct(String kw, int pageNum, int limit) {
        kw="%"+kw+"%";
        int start=(pageNum-1)*limit;
        List<ProductVO> productVOS = productMapper.selectProductByKeyword(kw, start, limit);

        Example example = new Example(Product.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andLike("productName",kw);
        int count=productMapper.selectCountByExample(example);

        int pageCount=count%limit==0? count/limit :count/limit+1;
        PageHelper<ProductVO> pageHelper = new PageHelper<>(count, pageCount, productVOS);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", pageHelper);
        return resultVO;
    }

    @Override
    public ResultVO listBrands(String kw) {
        kw="%"+kw+"%";
        List<String> brands = productMapper.selectBrandByKeyword(kw);
        return new ResultVO(ResStatus.OK,"success",brands);
    }
}
