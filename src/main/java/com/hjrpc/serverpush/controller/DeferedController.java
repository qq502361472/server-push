package com.hjrpc.serverpush.controller;

import com.hjrpc.serverpush.util.MessageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/defered")
public class DeferedController {

    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @RequestMapping("/getDefered")
    @ResponseBody
    public DeferredResult<Map<String,Integer>> getDefered(){
        DeferredResult<Map<String, Integer>> deferredResult = new DeferredResult<>();
        executorService.submit(()->{
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deferredResult.setResult(MessageUtils.getRandomMap());
        });
        return deferredResult;
    }
}
