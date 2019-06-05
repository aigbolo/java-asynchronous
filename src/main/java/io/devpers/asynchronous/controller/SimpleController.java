package io.devpers.asynchronous.controller;

import io.devpers.asynchronous.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "simple/")
public class SimpleController {

    Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private SimpleService simpleService;

    @GetMapping(path = "promiseAll")
    public @ResponseBody List<String> promissAll() throws InterruptedException, ExecutionException {
        // Start the clock
        long start = System.currentTimeMillis();
        logger.debug("start simple()");
        CompletableFuture<String> thread1 = this.simpleService.helloPeople(start, 3000,"Bob");
        CompletableFuture<String> thread2 = this.simpleService.helloPeople(start,2000, "Justin");
        CompletableFuture<String> thread3 = this.simpleService.helloPeople(start,4000, "Toon");
        CompletableFuture<String> thread4 = this.simpleService.helloPeople(start,1000, "Martin");

        //This is promise all behaviour
        CompletableFuture.allOf(thread1,thread2,thread3,thread4).join();
        logger.debug("Elapsed time: {}",System.currentTimeMillis()-start);
        List<String> hellos = new ArrayList<>();
        hellos.add(thread1.get());
        hellos.add(thread2.get());
        hellos.add(thread3.get());
        hellos.add(thread4.get());
        return hellos;
    }

    @GetMapping(path = "addWhenReady")
    public @ResponseBody List<String> addWhenReady() throws InterruptedException, ExecutionException {
        List<String> hellos = new ArrayList<>();
        // Start the clock
        long start = System.currentTimeMillis();
        logger.debug("start simple()");
        CompletableFuture<String> thread1 = this.simpleService.helloPeople(start, 1000,"Bob");
        CompletableFuture<String> thread2 = this.simpleService.helloPeople(start,2000, "Justin");
        CompletableFuture<String> thread3 = this.simpleService.helloPeople(start,1000, "Toon");
        CompletableFuture<String> thread4 = this.simpleService.helloPeople(start,3000, "Martin");

        // Add to list when thread done
        thread1.whenComplete((result,ex)->hellos.add(result));
        thread2.whenComplete((result,ex)->hellos.add(result));
        thread3.whenComplete((result,ex)->hellos.add(result));
        thread4.whenComplete((result,ex)->hellos.add(result));

        // This is promise all behaviour
        CompletableFuture.allOf(thread1,thread2,thread3,thread4).join();
        logger.debug("Elapsed time: {}",System.currentTimeMillis()-start);
        return hellos;
    }
}
