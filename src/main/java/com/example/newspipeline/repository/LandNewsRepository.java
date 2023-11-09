package com.example.newspipeline.repository;

import com.example.newspipeline.domain.LandNews;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * PackageName 	: com.example.newspipeline.repository
 * FileName 	: LandNewsRepository
 * Author 		: jhchoi
 * Date 		: 2023-11-09
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-09			jhchoi				최초 생성
 */
@Repository
public interface LandNewsRepository extends MongoRepository<LandNews, Long> {
}
