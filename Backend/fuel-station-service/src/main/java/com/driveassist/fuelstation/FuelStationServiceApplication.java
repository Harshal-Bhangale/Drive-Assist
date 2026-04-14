package com.driveassist.fuelstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.driveassist.fuelstation", "com.driveassist.common"})
@EnableDiscoveryClient
public class FuelStationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FuelStationServiceApplication.class, args);
    }
}
