package com.min.dao;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws")
public class WebSocketDao {
    private Session session;
    public int s=0;
    private static int onlineCount=0;
    public static CopyOnWriteArraySet<WebSocketDao> wbSockets = new CopyOnWriteArraySet<WebSocketDao>();
    @OnOpen
    public void onOpen(Session session) throws Exception{
        this.session=session;
        wbSockets.add(this);
        System.out.println("一个客户端连接进来了 它是"+session.getId());

    }
    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        wbSockets.remove(this);
        System.out.println("一个客户端关闭了，它是"+session.getId());
    }
    @OnError
    public void onError(Throwable t){
        t.printStackTrace();
    }
    public void sendMessageAll(String message) throws IOException{
        System.out.println(this.session.getId());
        this.session.getBasicRemote().sendText(message);
    }
}
