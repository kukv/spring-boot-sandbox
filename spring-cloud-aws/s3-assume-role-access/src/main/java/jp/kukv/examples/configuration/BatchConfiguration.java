package jp.kukv.examples.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BatchConfiguration {

  @Bean
  String tmpDir(@Value("${batch.tmp-dir:}") String value) {
    return value;
  }
}
