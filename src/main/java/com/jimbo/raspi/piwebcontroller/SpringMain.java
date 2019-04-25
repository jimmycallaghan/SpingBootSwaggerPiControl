package com.jimbo.raspi.piwebcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySources(value = {
        @PropertySource(ignoreResourceNotFound = true, value = "classpath:**/application.properties")
})
@EnableAsync
@EnableScheduling
public class SpringMain {
    public static void main(String[] args) {

        SpringApplication.run(SpringMain.class, args);

    }
}
