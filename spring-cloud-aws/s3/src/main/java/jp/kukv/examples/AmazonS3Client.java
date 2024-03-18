package jp.kukv.examples;

import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class AmazonS3Client {

  Logger log = LoggerFactory.getLogger(AmazonS3Client.class);

  S3Template s3Template;
  String bucket;
  String tmpDir;

  File receive(ObjectKey objectKey) {
    S3Resource resource = s3Template.download(bucket, objectKey.toString());
    try (InputStream inputStream = resource.getInputStream()) {
      log.info("filename: {}, size: {}", resource.getFilename(), resource.contentLength());

      File file = new File(tmpDir + resource.getFilename());
      Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

      return file;
    } catch (IOException e) {
      throw new IllegalArgumentException(
          String.format("ファイルが存在しません。 bucket: %s, file: %s", bucket, objectKey));
    }
  }

  void transfer(ObjectKey objectKey, File file) {
    try {
      log.info("filename: {}, size: {}", file.getName(), file.length());

      s3Template.upload(bucket, objectKey.toString(), new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(String.format("ファイルが存在しません。 file: %s", file.getName()));
    }
  }

  AmazonS3Client(S3Template s3Template, String bucket, String tmpDir) {
    this.s3Template = s3Template;
    this.bucket = bucket;
    this.tmpDir = tmpDir;
  }
}
