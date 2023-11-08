package com.example.newspipeline.config;

import com.example.newspipeline.domain.News;
import com.example.newspipeline.enums.TopicEnum;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * PackageName 	: com.example.newspipeline.config
 * FileName 	: StreamsConfig
 * Author 		: jhchoi
 * Date 		: 2023-11-07
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-07			jhchoi				최초 생성
 */
@Configuration
public class StreamsConfiguration {
	// Kafka Streams Configuration
	public static Properties getConfiguration() {
		Properties props = new Properties();
		// Streams Application ID 지정
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "news-pipeline");
		// Kafka 서버 지정
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		// Default Key Serde를 Long으로 선언
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
		// Default Value Serde를 String으로 선언(JSON)
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		// Kafka 초기 Offset이 없거나 범위를 벗어난 경우에 대한 설정으로 earliest는 가장 처음 offset부터 consuming
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return props;
	}

	// Topic 및 Kafka Template 생성
	@Bean
	public NewTopic newsTopic() {
		return TopicBuilder.name(TopicEnum.NEWS_TOPIC.getType())
			.partitions(2)
			.replicas(1)
			.build();
	}

	@Bean
	public NewTopic ecoNewsTopic() {
		return TopicBuilder.name(TopicEnum.ECO_TOPIC.getType())
			.partitions(2)
			.replicas(1)
			.build();
	}

	@Bean
	public NewTopic landNewsTopic() {
		return TopicBuilder.name(TopicEnum.LAND_TOPIC.getType())
			.partitions(2)
			.replicas(1)
			.build();
	}

	// Kafka Template 설정
	@Bean
	public KafkaTemplate<Long, News> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	/** PRIVATE METHODS **/
	// Kafka Template 을 만들기 위한 ProducerFactory
	private ProducerFactory<Long, News> producerFactory() {
		// Kafka Producer 설정 정보를 여기서 로드합니다.
		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class); // Key가 Long 타입이기 때문에 LongSerializer를 선언
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // Value가 Object형이기 때문에 JsonSerializer를 선언
		// Kafka Template 사용 시 DefaultKafkaProducerFactory 와 함께 사용하면
		// Thread safe 하게 기능을 구현할 수 있습니다.
		return new DefaultKafkaProducerFactory<>(configs);
	}
}

