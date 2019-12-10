package raven.nnitq.restdm.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Raven
 */
@SpringBootApplication(scanBasePackages = "raven.nnitq.restdm")
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
