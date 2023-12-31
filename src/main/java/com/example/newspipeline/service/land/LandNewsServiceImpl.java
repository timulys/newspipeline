package com.example.newspipeline.service.land;

import com.example.newspipeline.domain.LandNews;
import com.example.newspipeline.domain.News;
import com.example.newspipeline.repository.LandNewsRepository;
import com.example.newspipeline.utils.MongoQueryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PackageName 	: com.example.newspipeline.service.land
 * FileName 	: LandNewsServiceImpl
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
public class LandNewsServiceImpl implements LandNewsService {
	private final MongoTemplate mongoTemplate;
	private final LandNewsRepository landNewsRepository;

	@Override
	@Transactional
	public void saveLandNews(News news) {
		landNewsRepository.save(LandNews.of(news));
	}

	@Override
	@Transactional(readOnly = true)
	public List<LandNews> findAll() {
		return mongoTemplate.find(MongoQueryUtil.defaultSearch(100), LandNews.class);
	}
}
