package com.example.newspipeline.service.eco;

import com.example.newspipeline.domain.EcoNews;
import com.example.newspipeline.domain.News;
import com.example.newspipeline.repository.EcoNewsRepository;
import com.example.newspipeline.utils.MongoQueryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PackageName 	: com.example.newspipeline.service.eco
 * FileName 	: EcoServiceImpl
 * Author 		: jhchoi
 * Date 		: 2023-11-09
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-09			jhchoi				최초 생성
 */
@Service
@RequiredArgsConstructor
public class EcoNewsServiceImpl implements EcoNewsService {
	private final MongoTemplate mongoTemplate;
	private final EcoNewsRepository ecoNewsRepository;

	@Override
	public void saveEcoNews(News news) {
		ecoNewsRepository.save(EcoNews.of(news));
	}

	@Override
	public List<EcoNews> findAll() {
		return mongoTemplate.find(MongoQueryUtil.defaultSearch(100), EcoNews.class);
	}
}
