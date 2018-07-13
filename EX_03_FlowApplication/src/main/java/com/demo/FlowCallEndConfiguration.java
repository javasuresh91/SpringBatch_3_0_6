package com.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowCallEndConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step myStep() {
		return stepBuilderFactory.get("my Step execute Before Flow Execution Complete").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println(" Step Exected Before Flow Execution");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	/**
	 * In This JOb the Above step is executed and then the Flow execution is carried 
	 */
	@Bean
	public Job jobs(Flow flow) {
		return jobBuilderFactory.get("my Second job").start(myStep()).on("COMPLETED").to(flow).end().build();
	}
}
