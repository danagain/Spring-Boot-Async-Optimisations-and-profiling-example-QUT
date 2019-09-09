package com.huffer.java;


import com.huffer.java.qut.Sequential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class AppEntryPoint implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger LOGGER = LoggerFactory.getLogger(AppEntryPoint.class);

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            LOGGER.info("AppEntryPoint :: onApplicationEvent() :: Application Starting");
            TimeUnit.SECONDS.sleep(30); // Timeout to hook in JProfiler
            long startTime = System.nanoTime();
            Sequential.run();
            LOGGER.info("AppEntryPoint :: onApplicationEvent() :: Application Ending");
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println(totalTime);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}