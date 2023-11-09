package com.example.newspipeline.service;

import com.example.newspipeline.utils.crawlers.EcoCrawler;
import com.example.newspipeline.utils.crawlers.LandCrawler;
import com.example.newspipeline.domain.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

/**
 * PackageName 	: com.example.newspipeline.service
 * FileName 	: TelegramSender
 * Author 		: jhchoi
 * Date 		: 2023-11-06
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-06			jhchoi				최초 생성
 */
@Service
@RequiredArgsConstructor
public class TelegramSendService {
	private static final EcoCrawler ecoCrawler = new EcoCrawler();
	private static final LandCrawler landCrawler = new LandCrawler();
	private static final String ECO_TOKEN = "";
	private static final String LAND_TOKEN = "";
	private static final String ECO_CHAT_ID = "";
	private static final String LAND_CHAT_ID = "";

	public void send() {
		// 부동산 뉴스 묶음 전송
		String landArticle = "";
		List<News> landNewsList = landCrawler.getLandNews();
		if (landNewsList.size() > 0) {
			for (int i = 0; i < landNewsList.size(); i++) {
				landArticle += landNewsList.get(i).title() + landNewsList.get(i).url() + "%0A%0A";
			}
			sendMessage(landArticle, LAND_TOKEN, LAND_CHAT_ID);
		}

		// 부동산+경제 뉴스 묶음 전송

		List<News> ecoNewsList = landNewsList;
		ecoNewsList.addAll(ecoCrawler.getEcoNews());
		String article = "";
		for (int i = 0; i < ecoNewsList.size(); i++) {
			article += ecoNewsList.get(i).title() + ecoNewsList.get(i).url() + "%0A%0A";
			if (i != 0 && i % 10 == 0) {
				sendMessage(article, ECO_TOKEN, ECO_CHAT_ID);
				article = "";
			} else if (i == ecoNewsList.size() - 1) {
				sendMessage(article, ECO_TOKEN, ECO_CHAT_ID);
				article = "";
			}
		}
	}

	/** PRIVATE METHODS **/
	private void sendMessage(String article, String TOKEN, String CHAT_ID) {
		BufferedReader in = null;
		if (article != "") {
			try {
				String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
				URL obj = new URL("https://api.telegram.org/bot" + TOKEN + "/sendmessage?chat_id=" + CHAT_ID + "&text= " + dateTime + "%0A%0A" + article + "&parse_mode=markdown");
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
