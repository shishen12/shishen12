package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.ProductComments;
import com.qfedu.fmmall.entity.ProductCommentsVO;
import com.qfedu.fmmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentsMapper extends GeneralDAO<ProductComments> {

        /**
         * 根据商品id查询评论信息
         * @param productId
         * @param start
         * @param limit
         * @return
         */
        public List<ProductCommentsVO> selectCommontsByProductId(@Param("productId") String productId,
                                                                 @Param("start") int start,
                                                                 @Param("limit") int limit);

        public List<ProductCommentsVO> selectCommontsByuserId(@Param("userId") int userId,
                                                              @Param("start") int start,
                                                              @Param("limit") int limit);
}
