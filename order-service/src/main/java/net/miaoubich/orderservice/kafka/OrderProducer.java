package net.miaoubich.orderservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.miaoubich.basedomains.dto.OrderEvent;

@Service
@Slf4j
@AllArgsConstructor
public class OrderProducer {

	private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
	private NewTopic topic;
	
	public void sendMessage(OrderEvent event) {
		log.info("Order Event => %s", event.toString());
		
		//create message
		Message<OrderEvent> message = MessageBuilder
						.withPayload(event)
						.setHeader(KafkaHeaders.TOPIC, topic.name())
						.build();
		kafkaTemplate.send(message);
	}
}
