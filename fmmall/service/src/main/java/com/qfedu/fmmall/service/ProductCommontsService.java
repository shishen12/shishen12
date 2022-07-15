package com.qfedu.fmmall.service;

import com.qfedu.fmmall.entity.ProductComments;
import com.qfedu.fmmall.vo.ResultVO;

public interface ProductCommontsService {

    /**
     *根据商品id实现评论的分页查询
     * @param productId
     * @param pageNum
     * @param limit
     * @return
     */
    public ResultVO listCommentsByProductId(String productId,int pageNum,int limit);

    /**
     * 根据当前商品ID统计当前商品的评论信息
     * @param productId
     * @return
     */
    public ResultVO getCommentsCountByProductId(String productId);


    public int submitComment(ProductComments productComments);

}
