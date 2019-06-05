package io.devpers.asynchronous.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SimpleService {
    Logger logger = LoggerFactory.getLogger(SimpleService.class);

    @Async
    public CompletableFuture<String> helloPeople(Long start, Integer sleep,String name) throws InterruptedException {
        Thread.sleep(sleep);
        logger.trace("Hello {} Elapsed time: {}", name, System.currentTimeMillis()-start);
        return CompletableFuture.completedFuture("Hello "+name);
    }
}
