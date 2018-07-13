package com.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepTranstionConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("Step 1 ").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("This is a step 1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("Step 2 ").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("This is a step 2");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("Step 3 ").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("This is a step 3");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	/**
	 * @Transition means, Execution of step by step
	 * @Steps can be re-ordering and repeated N no of times, This means flow of the
	 *        JOb
	 * @This flow of job doesn't have any additional information
	 */
	@Bean
	public Job jobTransitionExampleOne() {
		return jobBuilderFactory.get("Transition Job Without Additioanl Info").start(step1()).next(step2())
				.next(step3()).build();
	}

	/**
	 * @Flow of Job with additional information
	 */
	@Bean
	public Job jobTransitionExamplesTwo() {
		return jobBuilderFactory.get("Transitionsd Job with Additioanl Info").start(step1()).on("COMPLETED").to(step2())
				.from(step2()).on("COMPLETED").to(step3()).from(step3()).end().build();
	}
	/**
	 * 
	 * @Manually MAking the step to fail
	 */
	@Bean
	public Job jobTransitionExamplesThree() {
		return jobBuilderFactory.get("Transitionsd Job with Failure Case")
				.start(step1())
				.on("COMPLETED").to(step2())
				.from(step2()).on("COMPLETED").fail() 
				.from(step3()).end().build();
	}
	/**
	 * @Manually Stop and restarting the Step3
	 */
	@Bean
	public Job jobTransitionExamplesFour() {
		return jobBuilderFactory.get("Transitionsd Job with Failure Case")
				.start(step1())
				.on("COMPLETED").to(step2())
				.from(step2()).on("COMPLETED").stopAndRestart(step3())
				.from(step3()).end().build();
	}
}
