package com.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Split Functionality is used run the two different Flow simultaneously/parellel
 */
@SpringBootApplication
@EnableBatchProcessing
public class BatchSplitApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchSplitApplication.class, args);
	}

}
