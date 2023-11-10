package com.example.newspipeline.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * PackageName 	: com.example.newspipeline.domain
 * FileName 	: EcoNews
 * Author 		: jhchoi
 * Date 		: 2023-11-09
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-09			jhchoi				최초 생성
 */
@Getter
@Document(collection = "eco")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EcoNews {
	@Id
	private Long newsId;
	private String url;
	private String title;

	public static EcoNews of(News news) {
		return new EcoNews(news.newsId(), news.url(), news.title());
	}
}
