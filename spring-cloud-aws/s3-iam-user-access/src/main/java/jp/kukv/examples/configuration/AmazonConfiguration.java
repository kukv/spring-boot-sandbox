package jp.kukv.examples.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AmazonConfiguration {

  @Bean
  String bucket(@Value("${spring.cloud.aws.s3.bucket:}") String value) {
    return value;
  }
}
