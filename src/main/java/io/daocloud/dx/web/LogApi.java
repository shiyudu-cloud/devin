package io.daocloud.dx.web;

import io.daocloud.dx.service.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Devin
 * @Date: 2020-03-27 10:12
 * @Version 1.0
 */
@RestController
public class LogApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogApi.class);

    @Autowired
    private LoggerService loggerService;
    @GetMapping("/simple")
    public void simple(){
        loggerService.simple();
        LOGGER.debug("这是一个debug日志");
    }

}
