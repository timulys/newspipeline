package com.example.newspipeline.config.consumer;

import com.example.newspipeline.domain.News;
import com.example.newspipeline.service.eco.EcoNewsService;
import com.example.newspipeline.service.land.LandNewsService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * PackageName 	: com.example.newspipeline.service.consumer
 * FileName 	: NewsConsumerService
 * Author 		: jhchoi
 * Date 		: 2023-11-07
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-07			jhchoi				최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class NewsConsumer {
	private final EcoNewsService ecoNewsService;
	private final LandNewsService landNewsService;
	@Bean
	public Consumer<KStream<Long, News>> ecoService() {
		return newsStream -> newsStream.foreach((key, news) -> {
			ecoNewsService.saveEcoNews(news);
			System.out.println(String.format("Eco NEWS Url[%s] Type[%s] Title[%s]", news.url(), news.type(), news.title()));
		});
	}

	@Bean
	public Consumer<KStream<Long, News>> landService() {
		return newsStream -> newsStream.foreach((key, news) -> {
			landNewsService.saveLandNews(news);
			System.out.println(String.format("Land NEWS Url[%s] Type[%s] Title[%s]", news.url(), news.type(), news.title()));
		});
	}
}
