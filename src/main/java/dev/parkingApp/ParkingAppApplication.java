package dev.parkingApp;

import dev.parkingApp.dtos.CoordinatesDTO;
import dev.parkingApp.dtos.SpotDTO;
import dev.parkingApp.services.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

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
