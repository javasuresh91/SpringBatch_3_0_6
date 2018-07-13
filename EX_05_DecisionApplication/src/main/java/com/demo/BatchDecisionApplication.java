package com.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Decision Functionality is used to determine which step need to run based on the status of the previous step
 */
@SpringBootApplication
@EnableBatchProcessing
public class BatchDecisionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchDecisionApplication.class, args);

	}

}
