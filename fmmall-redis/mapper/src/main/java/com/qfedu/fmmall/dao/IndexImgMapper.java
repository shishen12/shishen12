package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.IndexImg;
import com.qfedu.fmmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexImgMapper extends GeneralDAO<IndexImg> {
    //查询轮播图信息，status=1 按seq排序
    public List<IndexImg> listIndexImgs();
}
