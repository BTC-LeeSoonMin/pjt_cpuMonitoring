package com.pjt.cpumonitoring;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class HomeController {
    @GetMapping("/hello")
    public String home() {
        log.info("hello");
        return "This is Back Data";
    }
}
