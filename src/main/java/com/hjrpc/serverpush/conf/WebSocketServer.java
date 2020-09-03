package com.hjrpc.serverpush.conf;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/wsServer/{userId}")
public class WebSocketServer {

    public static ConcurrentHashMap<String, Session> map = new ConcurrentHashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        map.put(userId, session);
        System.out.println(map.size());
    }

    @OnClose
    public void onClose(Session session) {
        Iterator<Map.Entry<String, Session>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Session> next = iterator.next();
            if (next.getValue() == session) {
                iterator.remove();
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") String userId) throws IOException {
        String[] split = message.split(",");
        String toUser = split[0];
        String msg = split[1];
        if (!"1".equals(toUser)) {
            sendSingleMessage(toUser, msg,userId);
        } else {
            sendBroadMessage(msg,userId);
        }
    }

    @OnError
    public void onError(Session session, Throwable e) {
        e.printStackTrace();
    }

    public static void sendSingleMessage(String toUser, String message, String fromUser) throws IOException {
        Session session = map.get(toUser);
        if(session==null){
            return;
        }
        session.getBasicRemote().sendText(fromUser+",single,"+message);
    }

    public static void sendBroadMessage(String message, String fromUser) throws IOException {
        Iterator<Map.Entry<String, Session>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Session> next = iterator.next();
            Session session = next.getValue();
            session.getBasicRemote().sendText(fromUser+",broad,"+message);
        }
    }
}
