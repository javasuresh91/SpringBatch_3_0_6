package com.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This Flow Functionality is used to pack up the steps and execute in desire way 
 *
 */
@SpringBootApplication
@EnableBatchProcessing
public class BatchFlowApplication {

	public static void main(String[] args) {

		SpringApplication.run(BatchFlowApplication.class, args);
	}

}
