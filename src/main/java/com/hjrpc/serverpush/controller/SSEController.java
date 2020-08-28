package com.hjrpc.serverpush.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjrpc.serverpush.util.MessageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/sse")
public class SSEController {

    @RequestMapping(value = "/getSSE"/*,produces = "text/event-stream;charset=utf-8"*/)
    @ResponseBody
    public void getSSE(HttpServletResponse response){
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count=0;
        while (true) {
            count++;
            Map<String, Integer> randomMap = MessageUtils.getRandomMap();
            ObjectMapper objectMapper = new ObjectMapper();
            String s = null;
            try {
                s = objectMapper.writeValueAsString(randomMap);
                Thread.sleep(1000L);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            writer.write("data:"+s+"\n\n");
            System.out.println("data:"+s+"\n\n");
            writer.flush();
            if(count>100){
                return;
            }
        }
    }


}
