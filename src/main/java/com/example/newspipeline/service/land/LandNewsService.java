package com.example.newspipeline.service.land;

import com.example.newspipeline.domain.LandNews;
import com.example.newspipeline.domain.News;

import java.util.List;

/**
 * PackageName 	: com.example.newspipeline.service.land
 * FileName 	: LandNewsService
 * Author 		: jhchoi
 * Date 		: 2023-11-09
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-09			jhchoi				최초 생성
 */
public interface LandNewsService {
	void saveLandNews(News news);
	List<LandNews> findAll();
}
