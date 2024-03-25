package jp.kukv.examples.configuration;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
class SpringAsyncConfiguration {

  @Bean
  Executor asyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(4);
    executor.setQueueCapacity(6);
    executor.setMaxPoolSize(6);
    executor.setThreadNamePrefix("AsyncTask-");
    executor.initialize();
    return executor;
  }
}
