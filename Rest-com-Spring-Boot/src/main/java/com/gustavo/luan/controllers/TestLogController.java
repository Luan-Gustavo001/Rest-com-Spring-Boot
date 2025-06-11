package com.gustavo.luan.controllers;

import com.gustavo.luan.service.PersonServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestLogController {

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/test")
    public String testLog() {
        logger.info("Testando o log INFO!");
        logger.warn("Testando o log WARN!");
        logger.error("Testando o log ERROR!");
        logger.debug("Testando o log DEBUG!");
        return "Testando o log!";
    }
}
