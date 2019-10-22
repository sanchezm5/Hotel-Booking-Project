package com.company.hotelbookingeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HotelBookingEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingEurekaServerApplication.class, args);
	}

}
