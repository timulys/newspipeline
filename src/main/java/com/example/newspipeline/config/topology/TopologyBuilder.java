package com.example.newspipeline.config.topology;

import com.example.newspipeline.domain.News;
import com.example.newspipeline.enums.TopicEnum;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.kafka.support.serializer.JsonSerde;

/**
 * PackageName 	: com.example.newspipeline.service.topology
 * FileName 	: NewsTopology
 * Author 		: jhchoi
 * Date 		: 2023-11-07
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-07			jhchoi				최초 생성
 */
@RequiredArgsConstructor
public class TopologyBuilder {
	// 마지막 경제 뉴스 ID
	private static Long LAST_ECO_NEWS_ID = 0L;
	// 마지막 부동산 뉴스 ID
	private static Long LAST_LAND_NEWS_ID = 0L;

	// Kafka Streams Topology
	public static Topology build() {
		// Data Streams 를 위한 StreamsBuilder
		StreamsBuilder streamsBuilder = new StreamsBuilder();

		// Source Processor
		// News Topic 의 데이터를 꺼내와서 KStream을 만듭니다.
		KStream<Long, News> sourceStream = streamsBuilder.stream(TopicEnum.NEWS_TOPIC.getType(), Consumed.with(Serdes.Long(), new JsonSerde<>(News.class)));

		// sourceStream에 각각의 Topic Topology 를 구현합니다.
		ecoNewsStream(sourceStream);
		landNewsStream(sourceStream);

		return streamsBuilder.build();
	}

	private static void ecoNewsStream(KStream<Long, News> sourceStream) {
		// Streams Processor : 본격적인 데이터 처리를 하며 새로운 KStream을 만들어냅니다.
		KStream<Long, News> ecoNewsStream = sourceStream.filter((key, value) -> {
			// Recored의 Type이 eco이고, newsId가 최종 뉴스 ID보다 작으면 Consuming 합니다.
			if (value.type().equals("eco") && value.newsId() > LAST_ECO_NEWS_ID) {
				LAST_ECO_NEWS_ID = value.newsId();
				return true;
			}
			return false;
		});

		// Sink Processor : Streams Processor 에서 가공된 데이터를 원하는 목적지 Topic 으로 전송합니다.
		// News Topic -> 데이터 가공 -> Eco News Topic 으로 전송합니다.
		ecoNewsStream.to(TopicEnum.ECO_TOPIC.getType(), Produced.with(Serdes.Long(), new JsonSerde<>(News.class)));
	}

	private static void landNewsStream(KStream<Long, News> sourceStream) {
		// Streams Processor : 본격적인 데이터 처리를 하며 새로운 KStream을 만들어냅니다.
		KStream<Long, News> landNewsStream = sourceStream.filter((key, value) -> {
			// Recored의 Type이 land이고, newsId가 최종 뉴스 ID보다 작으면 Consuming 합니다.
			if (value.type().equals("land") && value.newsId() > LAST_LAND_NEWS_ID) {
				LAST_LAND_NEWS_ID = value.newsId();
				return true;
			}
			return false;
		});

		// Sink Processor : Streams Processor 에서 가공된 데이터를 원하는 목적지 Topic 으로 전송합니다.
		// News Topic -> 데이터 가공 -> Land News Topic 으로 전송합니다.
		landNewsStream.to(TopicEnum.LAND_TOPIC.getType(), Produced.with(Serdes.Long(), new JsonSerde<>(News.class)));
	}
}
