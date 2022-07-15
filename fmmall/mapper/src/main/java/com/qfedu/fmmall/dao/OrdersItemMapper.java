package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.OrdersItem;
import com.qfedu.fmmall.general.GeneralDAO;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersItemMapper extends GeneralDAO<OrdersItem> {

    public List<OrdersItem> listOrderItemsByOrderId(String orderId);
}
