package hyman.springbootdemo.rabbitmqBoot;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

// 创建生产者配置类，将RabbitAdmin、RabbitTemplate纳入Spring管理
@Configuration
public class BootProducerConfig {

    @Bean
    public Exchange exchange(){
        return new TopicExchange("bootexchange",true,false,new HashMap<>());
    }

}
