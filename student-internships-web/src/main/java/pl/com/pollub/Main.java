package pl.com.pollub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by mateusz on 16.10.16.
 */
@SpringBootApplication(scanBasePackages = "pl.com.pollub")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
