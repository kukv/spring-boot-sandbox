package jp.kukv.examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class Application implements ApplicationRunner {

  AmazonS3Client amazonS3Client;
  String tmpDir;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws IOException {
    File file = amazonS3Client.receive(new ObjectKey("logo.png"));

    // アップロード用に別名でコピー
    LocalDateTime dateTime = LocalDateTime.now();
    String datetimeString = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(dateTime);
    String fileName = "logo_" + datetimeString + ".png";
    Path transferPath = Path.of(tmpDir, fileName);
    Files.copy(file.toPath(), transferPath);

    ObjectKey objectKey = new ObjectKey(fileName);
    amazonS3Client.transfer(objectKey, file);
  }

  Application(AmazonS3Client amazonS3Client, String tmpDir) {
    this.amazonS3Client = amazonS3Client;
    this.tmpDir = tmpDir;
  }
}
