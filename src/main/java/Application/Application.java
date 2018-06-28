package Application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackageClasses = GreetingController.class)
@ComponentScan("Application")
@ComponentScan("Devices")
@ComponentScan("Calendar")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}