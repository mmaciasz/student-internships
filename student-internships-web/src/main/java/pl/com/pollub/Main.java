package pl.com.pollub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import pl.com.pollub.user.UserType;

/**
 * Created by mateusz on 16.10.16.
 */
@SpringBootApplication(scanBasePackages = "pl.com.pollub")
@PropertySource(value = {"classpath:myProperties.properties"})
public class Main {

    private static ApplicationContext appContext;

    public static void main(String[] args) {
        appContext = SpringApplication.run(Main.class, args);
        UserType.getAllValues();
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }
}
