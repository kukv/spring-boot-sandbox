package jp.kukv.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ExampleAsyncTaskExecutor {

    Logger log = LoggerFactory.getLogger(ExampleAsyncTaskExecutor.class);

    @Async
    CompletableFuture<Void> executeSleep1(String name) throws InterruptedException {
        log.info("print start: " + name + ", [" + Thread.currentThread().getName() + "]");
        Thread.sleep(1000);
        log.info("print end: " + name);

        return CompletableFuture.completedFuture(null);
    }

    @Async
    CompletableFuture<Void> executeSleep3(String name) throws InterruptedException {
        log.info("print start: " + name + ", [" + Thread.currentThread().getName() + "]");
        Thread.sleep(3000);
        log.info("print end: " + name);

        return CompletableFuture.completedFuture(null);
    }

    @Async
    CompletableFuture<Void> executeSleep5(String name) throws InterruptedException {
        log.info("print start: " + name + ", [" + Thread.currentThread().getName() + "]");
        Thread.sleep(5000);
        log.info("print end: " + name);

        return CompletableFuture.completedFuture(null);
    }
}
