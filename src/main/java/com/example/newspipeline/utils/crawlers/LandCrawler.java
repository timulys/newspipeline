package com.example.newspipeline.utils.crawlers;

import com.example.newspipeline.domain.News;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * PackageName 	: com.example.newspipeline.crawlers
 * FileName 	: LandCrawler
 * Author 		: jhchoi
 * Date 		: 2023-11-06
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-06			jhchoi				최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LandCrawler {
	@Value("${crawling.landnews}")
	private String LANDNEWS;

	public List<News> getLandNews() {
		List<News> landNewsList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(LANDNEWS).get();
			Elements elements = doc.select("div.cont");

			for (int i = elements.size() - 1; i >= 1; i--) {
				String url = "https:" + elements.get(i).childNode(3).childNode(1).attr("href");
				String title = elements.get(i).childNode(1).childNode(1).unwrap().toString();
				Long newsId = Long.valueOf(url.substring(url.lastIndexOf("/") + 1));
				News landNews = new News("land", url, title, newsId);
				landNewsList.add(landNews);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		return landNewsList;
	}
}
