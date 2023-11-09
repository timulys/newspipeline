package com.example.newspipeline.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

/**
 * PackageName 	: com.example.newspipeline.utils
 * FileName 	: MongoUtil
 * Author 		: jhchoi
 * Date 		: 2023-11-09
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-09			jhchoi				최초 생성
 */
public class MongoQueryUtil {
	/**
	 * Mongo DB Default Find Query
	 * with Sort and limit 10
	 * @return
	 */
	public static Query deafultSort(int limit) {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "newsId")).limit(limit);
		return query;
	}
}
