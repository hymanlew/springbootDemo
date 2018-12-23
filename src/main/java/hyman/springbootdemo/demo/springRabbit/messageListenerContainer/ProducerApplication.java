package hyman.springbootdemo.demo.springRabbit.messageListenerContainer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hyman.springbootdemo.rabbitmqSpring.messageConverter.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生产者启动类：
 *
 * RabbitAdmin: 用于声明交换机、队列、绑定等。
 * RabbitTemplate: 用于 RabbitMQ 消息的发送和接收。
 * MessageListenerContainer: 监听容器，为消息入队提供异步处理。
 */

@ComponentScan(basePackages = "hyman.springbootdemo.rabbitmqSpring.messageListenerContainer")
public class ProducerApplication {

    public static void sendString(RabbitTemplate template, String s){
        MessageProperties properties = new MessageProperties();
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        properties.setContentType("utf-8");
        Message message = new Message(s.getBytes(),properties);
        template.send("hymanexchange","test.me",message);
    }


    public static void main(String[] args) {
        // 获取注解的 bean 的上下文配置信息
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProducerApplication.class);

        RabbitAdmin rabbitAdmin = context.getBean(RabbitAdmin.class);
        RabbitTemplate template = context.getBean(RabbitTemplate.class);

        // 绑定交换机
        rabbitAdmin.declareExchange(new TopicExchange("hymanexchange",true,false,new HashMap<>()));

       sendString(template,"字符串");
    }
}
