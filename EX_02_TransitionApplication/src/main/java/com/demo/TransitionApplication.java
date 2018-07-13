package com.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This Transition techinque decides which steps need to be executes in ordered
 * way
 *
 */
@SpringBootApplication
@EnableBatchProcessing
public class TransitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransitionApplication.class, args);
	}

}
