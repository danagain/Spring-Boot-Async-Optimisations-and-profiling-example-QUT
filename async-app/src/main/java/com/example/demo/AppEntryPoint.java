package com.example.demo;

import com.example.demo.qut.Sequential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class AppEntryPoint implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Sequential sequential;

    public final static String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources";

    private final Logger LOGGER = LoggerFactory.getLogger(AppEntryPoint.class);

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            LOGGER.info("AppEntryPoint :: onApplicationEvent() :: Application Starting");
            TimeUnit.SECONDS.sleep(30);
            sequential.run();
            LOGGER.info("AppEntryPoint :: onApplicationEvent() :: Application Ending");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}