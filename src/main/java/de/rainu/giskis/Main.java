package de.rainu.giskis;

import de.rainu.giskis.consumer.FileConsumerChain;
import de.rainu.giskis.consumer.FileReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

  @Autowired
  private FileReceiver fileReceiver;
  @Autowired
  private FileConsumerChain chain;

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... strings) throws Exception {
    fileReceiver.watch(chain);
  }
}
