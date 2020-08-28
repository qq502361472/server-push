package com.hjrpc.serverpush.controller;

import com.hjrpc.serverpush.util.MessageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @RequestMapping("/getPrice")
    @ResponseBody
    public Map<String, Integer> getPrice() {
        Map<String, Integer> resultMap = MessageUtils.getRandomMap();
        System.out.println(resultMap);
        return resultMap;
    }

}
