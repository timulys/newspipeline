package com.example.newspipeline.repository;

import com.example.newspipeline.domain.EcoNews;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * PackageName 	: com.example.newspipeline.repository
 * FileName 	: EcoNewsRepository
 * Author 		: jhchoi
 * Date 		: 2023-11-09
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-09			jhchoi				최초 생성
 */
public interface EcoNewsRepository extends MongoRepository<EcoNews, Long> {
}
