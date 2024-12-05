package com.hospitallentesboulevard.opticaadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

@SpringBootApplication
public class OpticaAdminApplication {

    private static final Logger LOGGER = Logger.getLogger(OpticaAdminApplication.class.getName());

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-6"));
        LOGGER.info("Current time: " + new Date());
    }

    public static void main(String[] args) {
        SpringApplication.run(OpticaAdminApplication.class, args);
    }

}
