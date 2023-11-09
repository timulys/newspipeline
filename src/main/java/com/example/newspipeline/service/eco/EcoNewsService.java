package com.example.newspipeline.service.eco;

import com.example.newspipeline.domain.EcoNews;
import com.example.newspipeline.domain.News;

import java.util.List;

/**
 * PackageName 	: com.example.newspipeline.service.eco
 * FileName 	: EcoNewsService
 * Author 		: jhchoi
 * Date 		: 2023-11-09
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-09			jhchoi				최초 생성
 */
public interface EcoNewsService {
	void saveEcoNews(News news);
	List<EcoNews> findAll();
}
