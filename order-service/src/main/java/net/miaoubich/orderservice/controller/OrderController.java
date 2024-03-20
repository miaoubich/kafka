package net.miaoubich.orderservice.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.miaoubich.basedomains.dto.Order;
import net.miaoubich.basedomains.dto.OrderEvent;
import net.miaoubich.orderservice.kafka.OrderProducer;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {

	private final OrderProducer orderProducer;
	
	@PostMapping("/order")
	public ResponseEntity<String> placeOrder(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderEvent orderEvent = new OrderEvent();
		
		orderEvent.setMessage("Order status is in pending state!");
		orderEvent.setStatus("Pending");
		orderEvent.setOrder(order);
		orderProducer.sendMessage(orderEvent);
		
		return ResponseEntity.ok("Order placed successfully ...");
	}
}
