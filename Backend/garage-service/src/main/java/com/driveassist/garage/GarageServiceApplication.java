package com.driveassist.garage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.driveassist.garage", "com.driveassist.common"})
@EnableDiscoveryClient
public class GarageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GarageServiceApplication.class, args);
    }
}
