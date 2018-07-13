package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 * Anyone Database is must, If you didn't give any datasource, Spring boot will use the in-build Database
 * Job is a flow of states or steps
 * Job Instance is the logic run of the job, it can be many instance for the each job.
 * Job Execution is the each run of the Job Instance, N no of Job execution will be there for a single Job Instance
 */
@SpringBootApplication
public class SpringApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);
	}
}
