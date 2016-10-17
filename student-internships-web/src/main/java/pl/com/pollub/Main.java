package pl.com.pollub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by mateusz on 16.10.16.
 */
@SpringBootApplication(scanBasePackages = "pl.com.pollub")
public class Main {

    public static ApplicationContext appContext;

    public static void main(String[] args) {
        appContext = SpringApplication.run(Main.class, args);
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }
}
