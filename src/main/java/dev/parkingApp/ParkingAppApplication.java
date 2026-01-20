package dev.parkingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingAppApplication {

	private static Initializer initializer;

	@Autowired
	public void setInitialLoader(Initializer initializer) {
		ParkingAppApplication.initializer = initializer;
	}

	public static void main(String[] args) {

		SpringApplication.run(ParkingAppApplication.class, args);
		initializer.initial();
	}

}
