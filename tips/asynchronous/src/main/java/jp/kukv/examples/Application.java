package jp.kukv.examples;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@SpringBootApplication
class Application implements ApplicationRunner {

  Logger log = LoggerFactory.getLogger(Application.class);

  ExampleAsyncTaskExecutor exampleAsyncTaskExecutor;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args).close();
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("async process start.");

    CompletableFuture<Void> name1 = exampleAsyncTaskExecutor.executeSleep1("Test1");
    CompletableFuture<Void> name2 = exampleAsyncTaskExecutor.executeSleep3("Test2");
    CompletableFuture<Void> name3 = exampleAsyncTaskExecutor.executeSleep5("Test3");
    CompletableFuture<Void> name4 = exampleAsyncTaskExecutor.executeSleep1("Test4");
    CompletableFuture<Void> name5 = exampleAsyncTaskExecutor.executeSleep3("Test5");
    CompletableFuture<Void> name6 = exampleAsyncTaskExecutor.executeSleep5("Test6");
    CompletableFuture<Void> name7 = exampleAsyncTaskExecutor.executeSleep5("Test7");
    CompletableFuture<Void> name8 = exampleAsyncTaskExecutor.executeSleep1("Test8");
    CompletableFuture<Void> name9 = exampleAsyncTaskExecutor.executeSleep1("Test9");
    CompletableFuture<Void> name10 = exampleAsyncTaskExecutor.executeSleep3("Test10");

    CompletableFuture.allOf(name1, name2, name3, name4, name5, name6, name7, name8, name9, name10).join();
    log.info("async process done.");
  }

  Application(ExampleAsyncTaskExecutor exampleAsyncTaskExecutor) {
    this.exampleAsyncTaskExecutor = exampleAsyncTaskExecutor;
  }
}
