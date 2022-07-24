package com.qfedu.fmmall.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/webSocket/{oid}")
public class WebSocketServer {

    private static ConcurrentHashMap<String,Session> sessionMap=new ConcurrentHashMap<>();

    @OnOpen
    public void open(@PathParam("oid") String orderId, Session session){
        System.out.println("......建立连接："+orderId);
        sessionMap.put(orderId,session);
    }

    @OnClose
    public void close(@PathParam("oid")String orderId){
        sessionMap.remove(orderId);
    }

    public static void sendMsg(String orderId,String msg){
        try {
            Session session = sessionMap.get(orderId);
            session.getBasicRemote().sendText(msg);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}