package com.example.newspipeline.service;

import com.example.newspipeline.crawlers.EcoCrawler;
import com.example.newspipeline.crawlers.LandCrawler;
import com.example.newspipeline.domain.News;
import com.example.newspipeline.enums.TopicEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PackageName 	: com.example.newspipeline.service
 * FileName 	: StreamsService
 * Author 		: jhchoi
 * Date 		: 2023-11-07
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-07			jhchoi				최초 생성
 */
@Service
@RequiredArgsConstructor
public class StreamsService {
	private final KafkaTemplate<Long, News> kafkaTemplate;
	private static final EcoCrawler ecoCrawler = new EcoCrawler();
	private static final LandCrawler landCrawler = new LandCrawler();

	public void crawl() {
		List<News> newsList = ecoCrawler.getEcoNews();
		newsList.addAll(landCrawler.getLandNews());
		newsList.forEach(news -> {
			kafkaTemplate.send(TopicEnum.NEWS_TOPIC.getType(), news);
			System.out.println("News ID = [" + news.type() + "] " + news.newsId());
		});
	}

}
