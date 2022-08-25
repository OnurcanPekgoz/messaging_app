package tr.com.argela.whatsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.argela.whatsapp.model.Message;
import tr.com.argela.whatsapp.service.KafkaProducerService;

@RestController
public class ProducerController {

	@Autowired
	public KafkaProducerService producerService;

	@PostMapping("/sendMessage/{topicName}")
	public void sendMessageToKafkaTopic(@PathVariable String topicName,@RequestBody Message message) {
		producerService.sendMessage(topicName,message);
	}

}