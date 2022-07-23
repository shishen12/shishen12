package com.qfedu.fmmall.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.qfedu.fmmall.service.OrderService;
import com.qfedu.fmmall.websocket.WebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay")
@Api(value = "提供支付相关的操作接口",tags="支付管理")
public class PayController {

    @Autowired
    private OrderService orderService;
    /*
    回调接口
     */
    @ApiOperation("微信支付回调接口")
    @RequestMapping("/callback")
    public String PaySuccess(HttpServletRequest request) throws Exception {
        System.out.println(".......callback");
        ServletInputStream is=request.getInputStream();
        byte[] bs=new byte[1024];
        int len=-1;
        StringBuilder builder=new StringBuilder();
        while ((len=is.read(bs))!=-1){
            builder.append(new String(bs,0,len));
        }
        String s=builder.toString();

        Map<String, String> map = WXPayUtil.xmlToMap(s);

        if(map!=null && "success".equalsIgnoreCase(map.get("result_code"))) {
            //支付成功

            //修改订单
            String orderId = map.get("out_trade_no");
            int i = orderService.updateOrderStatus(orderId, "2");

            //通过websocket连接，向前端推送消息
            WebSocketServer.sendMsg(orderId,"1");
            //响应微信支付平台
            if (i > 0) {
                HashMap<String, String> resp = new HashMap<>();
                resp.put("return_code", "success");
                resp.put("return_msg", "OK");
                resp.put("app_id", map.get("appid"));
                resp.put("return_code", "success");
                return WXPayUtil.mapToXml(resp);
            }
        }
        return null;
    }
}
