package io.daocloud.dx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: Devin
 * @Date: 2020-03-27 11:10
 * @Version 1.0
 */
@Service
public class LoggerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerService.class);

    public String simple(){
        LOGGER.warn("service warn....");
        return "simple";
    }
}
