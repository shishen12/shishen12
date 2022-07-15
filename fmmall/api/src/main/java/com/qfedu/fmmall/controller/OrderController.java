package com.qfedu.fmmall.controller;

import com.github.wxpay.sdk.WXPay;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.service.OrderService;
import com.qfedu.fmmall.service.job.MyPayConfig;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/order")
@Api(value = "提供订单相关的操作接口",tags="订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @ApiOperation("订单添加接口")
    @PostMapping("/add")
    public ResultVO add(String cids,@RequestBody Orders order){

        ResultVO resultVO=null;
        try{
            Map<String, String> orderInfo = orderService.addOrder(cids, order);
            String orderId=orderInfo.get("orderId");

            if(orderId!=null){

                //设置当前订单
                HashMap<String,String> data=new HashMap<>();
                data.put("body",orderInfo.get("productNames"));//商品描述=
                data.put("out_trade_no",orderId);//使用当前用户订单的编号作为当前支付交易的交易号
                data.put("fee_type","CNY");//支付币种
                //data.put("total_fee",order.getActualAmount()*100+"");//支付金额
                data.put("total_fee","1");
                data.put("trade_type","NATIVE");//交易类型
                data.put("notify_url","http://43.142.17.246:8080/pay/callback");//设置支付完成的回调方法接口

                //微信支付：申请支付链接
                WXPay wxPay=new WXPay(new MyPayConfig());
                //发送请求，获取响应
                Map<String, String> resp= wxPay.unifiedOrder(data);
                orderInfo.put("payUrl",resp.get("code_url"));

                resultVO=new ResultVO(ResStatus.OK,"提交订单成功",orderInfo);
            }else {
                resultVO=new ResultVO(ResStatus.NO,"提交订单失败",null);
            }

        }catch (SQLException e){
            e.printStackTrace();
            resultVO=new ResultVO(ResStatus.NO,"提交订单失败",null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultVO;
    }

    @ApiOperation("订单状态查询接口")
    @GetMapping("/status/{oid}")
    public ResultVO getOrderStatus(@PathVariable("oid") String orderId,@RequestHeader("token")String token){
        ResultVO resultVO = orderService.getOrderById(orderId);
        return  resultVO;
    }

    @ApiOperation("订单列表查询接口")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "userId",value = "用户ID",required = true),
            @ApiImplicitParam(dataType = "string",name = "status",value = "订单状态",required = false),
            @ApiImplicitParam(dataType = "int",name = "pageNum",value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit",value = "每页条数",required = true),
    })
    public ResultVO list(@RequestHeader("token") String token, String userId,String status,int pageNum,int limit){
        ResultVO resultVO = orderService.listOrders(userId, status, pageNum, limit);
        return  resultVO;
    }

    @ApiOperation("用户订单删除接口")
    @DeleteMapping("delete/{oid}")
    public ResultVO deleteOrder(@PathVariable("oid") String orderId,@RequestHeader("token")String token){
        int i =orderService.deleteOrder(orderId);
        if(i==1){
            return new ResultVO(ResStatus.OK,"success",null);
        }else {
            return new ResultVO(ResStatus.NO,"fail",null);
        }
    }

    @ApiOperation("用户确认收货接口")
    @PutMapping("confirm/{oid}")
    public ResultVO comfirmOrder(@PathVariable("oid") String orderId,@RequestHeader("token")String token){
        int i =orderService.comfirmOrder(orderId);
        if(i==1){
            return new ResultVO(ResStatus.OK,"success",null);
        }else {
            return new ResultVO(ResStatus.NO,"fail",null);
        }
    }

    @ApiOperation("用户完成评价修改订单状态接口")
    @PutMapping("complete/{oid}")
    public ResultVO completeOrder(@PathVariable("oid") String orderId,@RequestHeader("token")String token){
        int i =orderService.completeOrder(orderId);
        if(i==1){
            return new ResultVO(ResStatus.OK,"success",null);
        }else {
            return new ResultVO(ResStatus.NO,"fail",null);
        }
    }

}
