package dev.parkingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ParkingAppApplication {

	public static void main(String[] args) {

//		SpringApplication.run(ParkingAppApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(ParkingAppApplication.class, args);
		String[] beans = context.getBeanDefinitionNames();
		System.out.println("");
	}

}
