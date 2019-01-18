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

    // 由于该 demo 试例是在同一系统上的，而同一系统中同一 bean 对象不能声明两次，否则会无法启动。所以这里先注释掉。
    //@Bean
    //public Exchange exchange(){
    //    return new TopicExchange("bootexchange",true,false,new HashMap<>());
    //}

}
