package com.driveassist.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.driveassist.rental", "com.driveassist.common"})
@EnableDiscoveryClient
public class RentalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentalServiceApplication.class, args);
    }
}
