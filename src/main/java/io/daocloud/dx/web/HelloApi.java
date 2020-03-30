package io.daocloud.dx.web;

import io.daocloud.dx.utils.I18nMessageSourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Devin
 * @Date: 2020-03-30 11:28
 * @Version 1.0
 */
@RestController
public class HelloApi {

    @Autowired
    private I18nMessageSourceUtils i18nMessageSourceUtils;


    @GetMapping(value = "/welcome",produces = {"application/json;charset=UTF-8"})
    public String getMessage(){
        String welcome = i18nMessageSourceUtils.getMessage("welcome", null);
        return welcome;
    }
}
