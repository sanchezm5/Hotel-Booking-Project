package com.company.hotelbookingcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class HotelBookingCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingCloudConfigServerApplication.class, args);
	}

}
