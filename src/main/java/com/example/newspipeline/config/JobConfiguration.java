package com.example.newspipeline.config;

import com.example.newspipeline.tasklet.CrawlingTasklet;
import com.example.newspipeline.service.TelegramSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;

/**
 * PackageName 	: com.example.newspipeline.config
 * FileName 	: JobConfiguration
 * Author 		: jhchoi
 * Date 		: 2023-11-06
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-06			jhchoi				최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class JobConfiguration {
	private final JobRepository jobRepository;
	private final PlatformTransactionManager platformTransactionManager;
	private final JobLauncher jobLauncher;
	private final JobRegistry jobRegistry;

	@Bean
	public Job sendNewsJob() {
		return new JobBuilder("sendNewsJob", jobRepository)
			.start(crawlingStep(jobRepository, platformTransactionManager))
			.build();
	}

	private Step crawlingStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("sendNewsStep", jobRepository)
			.tasklet(new CrawlingTasklet(new TelegramSendService()), platformTransactionManager)
			.build();
	}

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() {
		JobRegistryBeanPostProcessor jobProcessor = new JobRegistryBeanPostProcessor();
		jobProcessor.setJobRegistry(jobRegistry);
		return jobProcessor;
	}

	@Scheduled(fixedDelay = 60 * 3000L) // 3분마다
	public void runJob() {
		try {
			jobLauncher.run(jobRegistry.getJob("sendNewsJob"), new JobParametersBuilder().addString("datetime", LocalDateTime.now().toString()).toJobParameters());
		} catch (NoSuchJobException e) {
			throw new RuntimeException(e);
		} catch (JobInstanceAlreadyCompleteException |
			JobExecutionAlreadyRunningException |
			JobParametersInvalidException |
			JobRestartException e) {
			throw new RuntimeException(e);
		}
	}
}
