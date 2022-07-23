package com.qfedu.fmmall.service.impl;

import com.mysql.cj.util.LogUtils;
import com.qfedu.fmmall.dao.*;
import com.qfedu.fmmall.entity.*;
import com.qfedu.fmmall.service.OrderService;
import com.qfedu.fmmall.utils.PageHelper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Console;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrdersItemMapper ordersItemMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    private Logger logger=LoggerFactory.getLogger(OrderServiceImpl.class);

    @Transactional
    public Map<String,String> addOrder(String cids, Orders order) throws SQLException {
        logger.info("add order begin...");
        Map<String,String> map=new HashMap<>();

        //根据cids查询当前订单中关联的购物车记录详情包括库存
        String[] arr=cids.split(",");
        List<Integer> cidsList=new ArrayList<>();
        for(int i=0;i< arr.length;i++){
            cidsList.add(Integer.parseInt(arr[i]));
        }
        List<ShoppingCartVO> list = shoppingCartMapper.selectShopcartByCids(cidsList);

        //校验库存
        boolean f=true;
        String untitled="";
        for(ShoppingCartVO sc:list){
            if(Integer.parseInt(sc.getCartNum())>sc.getSkuStock()){
                f=false;
            }
            //获取所有商品的名称，以逗号隔开拼接成字符串
            untitled+=sc.getProductName()+"  ";
        }
        if(f){
            //库存充足，保存订单
            //1.userId
            //2.untitled
            //3.收货人信息:姓名 电话地址
            //4.总价格
            //5.支付方式
            //6.订单创建时间
            //7.订单初始状态
            logger.info("product stock is OK...");
            order.setUntitled(untitled);
            order.setUpdateTime(new Date());
            order.setCreateTime(new Date());
            order.setStatus("1");

            //生成订单编号
            String orderId = UUID.randomUUID().toString().replace("-", "");
            order.setOrderId(orderId);

                //保存订单
                int i = ordersMapper.insert(order);

                //生成商品快照
                for(ShoppingCartVO sc:list){
                    int cnum=Integer.parseInt(sc.getCartNum());
                    String itemId=System.currentTimeMillis()+""+(new Random().nextInt(89999)+10000);
                    OrdersItem ordersItem = new OrdersItem(itemId, orderId, sc.getProductId(), sc.getProductName(), sc.getProductImg(), sc.getSkuId(), sc.getSkuName(), new BigDecimal(sc.getSellPrice()), cnum, new BigDecimal(sc.getSellPrice() * cnum), new Date(), new Date(), 0);
                    ordersItemMapper.insert(ordersItem);
                }

                //扣减库存  根据套餐ID修改套餐库存量
            for(ShoppingCartVO sc:list){
                String skuId = sc.getSkuId();
                int newStock=sc.getSkuStock()-Integer.parseInt(sc.getCartNum());
//                Example example = new Example(ProductSku.class);
//                Example.Criteria criteria=example.createCriteria();
//                criteria.andEqualTo("skuId",skuId);
                ProductSku productSku=new ProductSku();
                productSku.setSkuId(skuId);
                productSku.setStock(newStock);
                productSkuMapper.updateByPrimaryKeySelective(productSku);
            }


            //删除购物车：当购物车中的记录购买成功后，删除对于的购物车
            for(int cid:cidsList){
                shoppingCartMapper.deleteShopcartByCartId(cid);
            }


            logger.info("add order finished...");
            map.put("orderId",orderId);
            map.put("productNames",untitled);

            return map;
        }else {
            //库存不足
            return null;
        }
    }

    @Override
    public int updateOrderStatus(String orderId, String status) {
        Orders orders=new Orders();
        orders.setOrderId(orderId);
        orders.setStatus(status);
        int i = ordersMapper.updateByPrimaryKeySelective(orders);
        return i;
    }

    @Override
    public ResultVO getOrderById(String orderId) {
        Orders orders = ordersMapper.selectByPrimaryKey(orderId);
        return new ResultVO(ResStatus.OK,"success",orders.getStatus());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void closeOrder(String orderId) {
        synchronized (this){
            //修改订单状态 status=6(已关闭) close_type=1(超时未支付)
            Orders cancleOrder=new Orders();
            cancleOrder.setOrderId(orderId);
            cancleOrder.setStatus("6");
            cancleOrder.setCloseType(1);
            ordersMapper.updateByPrimaryKeySelective(cancleOrder);
            //还原库存
            Example example1 = new Example(OrdersItem.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("orderId",orderId);
            List<OrdersItem> ordersItems = ordersItemMapper.selectByExample(example1);

            for(int j=0;j<ordersItems.size();j++){
                OrdersItem ordersItem=ordersItems.get(j);

                //修改
                ProductSku productSku=productSkuMapper.selectByPrimaryKey(ordersItem.getSkuId());
                productSku.setStock(productSku.getStock()+ordersItem.getBuyCounts());
                productSkuMapper.updateByPrimaryKeySelective(productSku);
            }
        }
    }

    @Override
    public ResultVO listOrders(String userId, String status, int pageNum, int limit) {

        int start=(pageNum-1)*limit;
        List<OrdersVO> ordersVOS=ordersMapper.selectOrders(userId,status,start,limit);
        Example example=new Example(Orders.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andLike("userId",userId);
        if(status!=null && !"".equals(status)){
            criteria.andLike("status",status);
        }
        int count=ordersMapper.selectCountByExample(example);
        int pageCount=count%limit==0? count/limit :count/limit+1;
        PageHelper<OrdersVO> pageHelper = new PageHelper<>(count, pageCount,ordersVOS);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", pageHelper);
        return  resultVO;
    }

    @Override
    public int deleteOrder(String orderId) {
        int i = ordersMapper.deleteByPrimaryKey(orderId);
        return i;
    }

    @Override
    public int comfirmOrder(String orderId) {
        int i=ordersMapper.confirmOrder(orderId);
        return i;
    }

    @Override
    public int completeOrder(String orderId) {
        int i = ordersMapper.completeOrder(orderId);
        return i;
    }
}
