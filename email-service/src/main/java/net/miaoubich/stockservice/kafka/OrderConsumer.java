package net.miaoubich.stockservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import net.miaoubich.basedomains.dto.OrderEvent;

@Service
public class OrderConsumer {

	private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(OrderEvent orderEvent) {
		logger.info("Order event received in email service => " + orderEvent.toString());
		
		//TBD -> Send email to the customer about its order
	}
}
