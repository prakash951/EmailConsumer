package com.target.flow.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.target.flow.processor.EmailProcessor;

@Component
public class EmailRoute extends RouteBuilder {

	@Value("${smtp.url}")
	private String smtpAdresss;

	@Value("${smtp.from}")
	private String userName;

	@Value("${smtp.password}")
	private String password;

	@Value("${topic}")
	private String topicName;

	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaServer;

	@Autowired
	private EmailProcessor myEmailProcessor;

	@Override
	public void configure() throws Exception {
		from("kafka:" + topicName + "?brokers=" + kafkaServer + "&groupId=topic3").process(myEmailProcessor)
				.to(smtpAdresss + "?username=" + userName + "&password=" + password);

	}

}
