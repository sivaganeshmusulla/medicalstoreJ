package com.medicalstore;

import org.springframework.boot.SpringApplication;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//to register in Eureka client
@EnableDiscoveryClient
//service to service communication
@EnableFeignClients
@SpringBootApplication
public class StockServiceMain {

	public static void main(String[] args) {
		SpringApplication.run(StockServiceMain.class, args);
		
	}

}
