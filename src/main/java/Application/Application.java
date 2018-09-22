package Application;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("Application")
@ComponentScan("Devices")
@ComponentScan("Server")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }









}