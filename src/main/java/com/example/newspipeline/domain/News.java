package com.example.newspipeline.domain;

/**
 * PackageName 	: com.example.newspipeline.domain
 * FileName 	: News
 * Author 		: jhchoi
 * Date 		: 2023-11-06
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-06			jhchoi				최초 생성
 */
public record News(String type, String url, String title, Long newsId, Long newsDate, String summary) {
}
