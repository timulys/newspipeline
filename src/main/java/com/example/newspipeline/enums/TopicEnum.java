package com.example.newspipeline.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * PackageName 	: com.example.newspipeline.enums
 * FileName 	: TopicEnum
 * Author 		: jhchoi
 * Date 		: 2023-11-07
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-07			jhchoi				최초 생성
 */
@Getter
@RequiredArgsConstructor
public enum TopicEnum {
	NEWS_TOPIC("news"),
	ECO_TOPIC("eco-news"),
	LAND_TOPIC("land-news")
	;

	private final String type;
}
