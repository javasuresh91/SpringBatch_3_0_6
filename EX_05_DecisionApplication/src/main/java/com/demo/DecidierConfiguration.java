package com.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public final class DecidierConfiguration {
	

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public final Step startStep() {
		return stepBuilderFactory.get("Start Step").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("This is start tasklet");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step evenStep() {
		return stepBuilderFactory.get("Even Step").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("This is Even tasklet");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step oddStep() {
		return stepBuilderFactory.get("Odd Step").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("This is Odd tasklet");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public JobExecutionDecider decider() {
		return new CustomeDecider();
	}

	/**
	 * Desider is used inn the job, to determine the steps to be executed
	 */
	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").start(startStep()).next(decider()).from(decider()).on("ODD").to(oddStep())
				.from(decider()).on("EVEN").to(evenStep()).from(oddStep()).on("*").to(decider()).end().build();
	}

	/**
	 * Decider Implementation should be done like this
	 *
	 */
	public static class CustomeDecider implements JobExecutionDecider {
		private int flag = 0;

		@Override
		public FlowExecutionStatus decide(JobExecution arg0, StepExecution arg1) {
			flag++;
			System.out.println(flag);
			if (flag % 2 == 0) {
				return new FlowExecutionStatus("EVEN");
			} else {
				return new FlowExecutionStatus("ODD");
			}
		}

	}
}
