package hyman.springbootdemo.rabbitmqClient;

import com.rabbitmq.client.*;
import hyman.springbootdemo.rabbitmqClient.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;

public class MessageConsumer {

    private final static String QUEUE_NAME = "testqueue";

    public static void main(String[] args) {
        Channel channel = ChannelUtils.getchannel("test-channel-consumer");

        try {
            // 声明队列 (队列名, 是否持久化, 是否排他, 是否自动删除, 队列属性);
            AMQP.Queue.DeclareOk queue = channel.queueDeclare(QUEUE_NAME,true,false,false,new HashMap<>());

            // 声明交换机 (交换机名, 交换机类型, 是否持久化, 是否自动删除, 是否是内部交换机, 交换机属性);
            channel.exchangeDeclare("hymanexchange", BuiltinExchangeType.TOPIC,true,false,false,new HashMap<>());

            // 将队列 Binding 到交换机上 (队列名, 交换机名, binding key, 绑定属性);
            channel.queueBind(QUEUE_NAME,"hymanexchange","test.#",new HashMap<>());

            // 消费者订阅消息，监听如上声明的队列 (队列名, 是否自动应答(与消息可靠有关), 消费者标签, 消费者)
            channel.basicConsume(queue.getQueue(),true,"consumer1",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(consumerTag);
                    System.out.println(envelope.toString());
                    System.out.println(properties.toString());
                    System.out.println("消息内容："+new String(body));
                }
            });


        } catch (IOException e) {

            // 尽量不要自己处理掉异常，而是返回给上层调用。
            e.printStackTrace();
            System.out.println("接受消息失败！");
        }
    }

}
