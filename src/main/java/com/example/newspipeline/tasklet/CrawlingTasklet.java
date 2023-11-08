package com.example.newspipeline.tasklet;

import com.example.newspipeline.service.StreamsService;
import com.example.newspipeline.service.TelegramSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * PackageName 	: com.example.newspipeline.batch
 * FileName 	: CrawlingTasklet
 * Author 		: jhchoi
 * Date 		: 2023-11-06
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-06			jhchoi				최초 생성
 */
@Slf4j
@RequiredArgsConstructor
public class CrawlingTasklet implements Tasklet {
	private final TelegramSendService telegramSendService;
	private final StreamsService streamsService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.debug("executed tasklet");
		// Kafka 테스트를 위해 잠시 주석
		streamsService.crawl();
//		telegramSendService.send(); // Send 비즈니스 수행
		return RepeatStatus.FINISHED;
	}
}
