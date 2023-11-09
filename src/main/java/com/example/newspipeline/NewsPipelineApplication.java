package com.example.newspipeline;

import com.example.newspipeline.config.StreamsConfiguration;
import com.example.newspipeline.config.topology.TopologyBuilder;
import org.apache.kafka.streams.KafkaStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableMongoRepositories
public class NewsPipelineApplication {

	public static void main(String[] args) {
		var configuration = StreamsConfiguration.getConfiguration();
		var topology = TopologyBuilder.build();
		// Kafka Streams 생성!
		var kafkaStreams = new KafkaStreams(topology, configuration);

		kafkaStreams.cleanUp(); // 시작 전 내부를 cleanUp 해줍니다.
		kafkaStreams.start(); // KafkaStreams 기동
		SpringApplication.run(NewsPipelineApplication.class, args);

		// Application Runtime 종료 시 안정하게 종료합니다.
		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
	}

}
