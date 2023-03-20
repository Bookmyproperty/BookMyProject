package com.bookmyproperty.producer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.bookmyproperty.entity.Property;
import com.bookmyproperty.utils.ParsePropertyUtils;

import reactor.core.publisher.Mono;

@Service
public class PropertyProducer {
	
	@Value("${spring.kafka.template.default-topic}")
	private String topic;
	
	 
	private static final Logger log = LoggerFactory.getLogger(PropertyProducer.class);

	
	@Autowired
	KafkaTemplate<Long,String> kafkaTemplate;
	
	
	public Property register(Property property) {
		sendPropertToKafka(ParsePropertyUtils.getPropertyAsString(property), property.getId());
		return property;
		
	}
	
	public void sendPropertToKafka(String property,Long id) {
		ProducerRecord<Long, String> producerRecord=buildProducerRecord(id, property, topic);
		 CompletableFuture<SendResult<Long, String>> completableFuture =kafkaTemplate.send(producerRecord);
		 completableFuture.whenComplete((sendResult,th)->{
			 handleSuccess(id, property, sendResult);
		 }).exceptionally(th->{
			 handleFailure(id, property, th);
			return null; 
		 });
		
	}
	
	 private void handleFailure(Long key, String value, Throwable ex) {
	        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
	        try {
	            throw ex;
	        } catch (Throwable throwable) {
	            log.error("Error in OnFailure: {}", throwable.getMessage());
	        }


	    }
	 private void handleSuccess(Long key, String value, SendResult<Long, String> result) {
	        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
	    }
	
	private ProducerRecord<Long, String> buildProducerRecord(Long key, String value, String topic) {

        List<Header> recordHeaders = List.of(new RecordHeader("property-header", "book-my-property".getBytes()));
        return new ProducerRecord<>(topic, null, key, value, recordHeaders);
    }
	

}
