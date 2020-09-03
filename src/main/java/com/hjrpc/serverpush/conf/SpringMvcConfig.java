package com.hjrpc.serverpush.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ajaxPush").setViewName("/ajax_push");
        registry.addViewController("/defered").setViewName("/defered_comet");
        registry.addViewController("/sse").setViewName("/sse");
        registry.addViewController("/chat").setViewName("/chat");
        registry.addViewController("/wbChat").setViewName("/wbChat");
    }

}
