package com.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class SplitConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Tasklet getTasklet() {
		return new CustomeTasklet();
	}

	@Bean
	public Flow flow1() {

		return new FlowBuilder<Flow>("flow1").start(stepBuilderFactory.get("Step 1").tasklet(getTasklet()).build())
				.build();
	}

	public Flow flow2() {
		return new FlowBuilder<Flow>("flow2").start(stepBuilderFactory.get("step 2").tasklet(getTasklet()).build())
				.next(stepBuilderFactory.get("Step 3").tasklet(getTasklet()).build()).build();
	}

	/**
	 * Here Split is used to run the Flow 2 and flow 1 parallel
	 * 
	 */
	@Bean
	public Job job() {
		return jobBuilderFactory.get("job 1").start(flow1()).split(new SimpleAsyncTaskExecutor()).add(flow2()).end()
				.build();
	}

	public static class CustomeTasklet implements Tasklet {

		@Override
		public RepeatStatus execute(StepContribution stepContri, ChunkContext chunkCtx) throws Exception {
			System.out.println(chunkCtx.getStepContext().getStepName() + " has been executed on thread "
					+ Thread.currentThread().getName());
			return null;
		}

	}
}
