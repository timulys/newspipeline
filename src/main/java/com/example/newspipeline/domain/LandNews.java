package com.example.newspipeline.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * PackageName 	: com.example.newspipeline.domain
 * FileName 	: LandNews
 * Author 		: jhchoi
 * Date 		: 2023-11-09
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-09			jhchoi				최초 생성
 */
@Getter
@Document(collection = "land")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LandNews {
	@Id
	private Long newsId;
	private String url;
	private String title;
	private Long newsDate;
	private String summary;

	public static LandNews of(News news) {
		return new LandNews(news.newsId(), news.url(), news.title(), news.newsDate(), news.summary());
	}
}
