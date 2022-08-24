package pe.com.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
public class MicroService2Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroService2Application.class, args);
	}
}
