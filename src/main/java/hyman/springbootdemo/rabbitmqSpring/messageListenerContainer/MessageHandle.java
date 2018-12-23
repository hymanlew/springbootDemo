package hyman.springbootdemo.rabbitmqSpring.messageListenerContainer;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 自定义消费者消息处理器类：
 * 在消息处理器类中使用 @RabbitListener 注解声明该类为RabbitMQ消息处理器类，并在bindings属性中声明了队列和交换机已经它们之间
 * 的绑定关系，使用 @RabbitHandler 注解声明具体消息处理方法。
 */
@Component
@RabbitListener(bindings = {
        @QueueBinding(value = @Queue(value = "testqueue",durable = "true",autoDelete = "false",exclusive = "false"),
                      exchange = @Exchange(name = "hymanexchange"))})
public class MessageHandle {

    @RabbitHandler
    public void add(byte[] body) {
        System.out.println("----------byte[]方法进行处理----------");
        try {
            System.out.println(new String(body,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
