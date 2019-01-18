package hyman.springbootdemo.rabbitmqBoot;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerApplication {

	private static RabbitTemplate template;

	@Autowired
	public void setTemplate(RabbitTemplate template){
		ProducerApplication.template = template;
	}

	public static void main(String[] args) {
		// boot 主程序启动入口
		SpringApplication.run(ProducerApplication.class);

		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		messageProperties.setContentType("UTF-8");
		Message message = new Message(("boot 主生产信息").getBytes(), messageProperties);
		template.send("bootexchange", "add", message);
	}
}
