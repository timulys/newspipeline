package com.example.newspipeline.crawlers;

import com.example.newspipeline.domain.News;
import lombok.Getter;
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
 * FileName 	: EcoCrawler
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
public class EcoCrawler {
	private static final String ECONEWS = "";
	// 마지막 뉴스 이후 기사만 가져오기 위한 NewsId;
	private static Long LAST_ECO_NEWS_ID = 0L;

	public List<News> getEcoNews() {
		List<News> ecoNewsList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(ECONEWS).get();
			Elements elements = doc.select("div.cont_thumb");
			// 가져온 html 구조를 파싱하여 사용하면 됩니다.
			// 리팩토링해서 더 멋지게 사용하시면 더 좋지요.
			for (int i = elements.size() - 1; i >= 1; i--) {
				String url = "";
				String title = "";
				if (elements.get(i).childNode(1).childNodes().size() < 2) {
					url = elements.get(i).childNode(3).childNode(0).attr("href");
					title = elements.get(i).childNode(3).childNode(0).unwrap().toString();
				} else {
					url = elements.get(i).childNode(1).childNode(1).attr("href");
					title = elements.get(i).childNode(1).childNode(1).unwrap().toString();
				}
				Long newsId = Long.valueOf(url.substring(url.lastIndexOf("/") + 1));
				News ecoNews = new News("eco", url, title, newsId);
				ecoNewsList.add(ecoNews);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		return ecoNewsList;
	}
}
