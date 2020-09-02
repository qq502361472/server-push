package com.hjrpc.serverpush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StompController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/massRequest")
    @SendTo("/mass/getResponse")
    public Map<String, String> receiveMassMessage(Map<String, String> param) {
        String message = param.get("message");
        String sendFrom = param.get("sendFrom");

        HashMap<String, String> result = new HashMap<>();
        result.put("message", message);
        result.put("sendFrom", sendFrom);
        return result;
    }

    @MessageMapping("/queueRequest")
    public Map<String, String> receiveQueueMessage(Map<String, String> param) {
        String sendFrom = param.get("sendFrom");
        String sendTo = param.get("sendTo");
        String message = param.get("message");

        HashMap<String, String> result = new HashMap<>();
        result.put("sendFrom", sendFrom);
        result.put("sendTo", sendTo);
        result.put("message", message);
        simpMessagingTemplate.convertAndSendToUser(sendTo, "/alone",result);
        return result;
    }

}
