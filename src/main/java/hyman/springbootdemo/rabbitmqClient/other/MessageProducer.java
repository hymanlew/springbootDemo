package hyman.springbootdemo.rabbitmqClient.other;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import hyman.springbootdemo.rabbitmqClient.ChannelUtils;
import hyman.springbootdemo.util.Logutil;

import java.util.HashMap;
import java.util.Map;

/**
 * Alternate Exchange简称AE，当消息不能被正确路由时，如果交换机设置了AE则消息会被投递到AE中，如果存在AE链则会按此继续投递，直
 * 到消息被正确路由或AE链结束消息被丢弃。通常建议AE的交换机类型为 Fanout（广播式路由，即路由到所有与它绑定的Queue中）防止出现
 * 路由失败，如果一个交换机指定了AE，那么意为着该交换机和AE链都无法被正确路由时才会触发消息返回。
 *
 * alternate-exchange 是 RabbitMQ自己扩展的功能，不是AMQP协议定义的。在创建Exchange时指定该 x-arguments 为 alternate-exchange属性，
 * 则发送消息的时候根据route key没有找到可以投递的队列，这就会将此消息路由到 Alternate Exchange 属性指定的 Exchange (就是一个普通的
 * exchange)上了。
 * 并且它只支持附加到 DIRECT，FANOUT，header 类型的交换器上，不支持 TOPIC。
 */
public class MessageProducer {

    public static void main(String[] args) throws Exception{
        Channel channel = ChannelUtils.getchannel("test-channel-producer");

        // 声明交换机 AE 类型为Fanout (名, 类型, 持久化, 是否自动删除, 是否是内部交换机, 交换机属性);
        channel.exchangeDeclare("hymanAE", BuiltinExchangeType.FANOUT,true,false,false,new HashMap<>());

        // 设置交换机为 AE，并添加到交换器属性
        Map<String,Object> exchangeProperties = new HashMap<>();
        exchangeProperties.put("alternate-exchange","hymanAE");

        channel.exchangeDeclare("hymanDrect", BuiltinExchangeType.DIRECT,true,false,false,exchangeProperties);
        //channel.exchangeDeclare("hymanexchange", BuiltinExchangeType.TOPIC,true,false,false,exchangeProperties);
        //channel.exchangeDeclare("hymanFanout", BuiltinExchangeType.FANOUT,true,false,false,exchangeProperties);

        // 设置消息属性，deliveryMode（2）消息持久化，消息编码格式;
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder().deliveryMode(2).contentType("utf-8").build();

        // 生产者发布消息 (交换机名, 路由关键字 Routing key, 可靠消息相关属性, 消息属性, 消息体);
        String msg = "测试不能路由的信息";
        channel.basicPublish("hymanDrect","test123",false,basicProperties,msg.getBytes());

        Logutil.logger.info("send msg "+ msg + " to [hymanDrect] exchange !");
    }
}
