package hyman.springbootdemo.rabbitmqClient;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChannelUtils {

    private static Connection connect = null;

    // socket 与 rabbitmq 的连接工厂
    public static ConnectionFactory getConnectFactory(){
        ConnectionFactory factory = new ConnectionFactory();

        // 配置连接信息
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("hymanhost");
        factory.setUsername("hyman");
        factory.setPassword("hyman");

        // 网络异常自动连接恢复
        factory.setAutomaticRecoveryEnabled(true);
        // 每10秒尝试重试连接一次
        factory.setNetworkRecoveryInterval(10000);

        // 设置ConnectionFactory属性信息
        Map<String,Object> factoryPropertiesMap = new HashMap<>();
        factoryPropertiesMap.put("name","hyman");
        factoryPropertiesMap.put("ip","127.0.0.1");
        factory.setClientProperties(factoryPropertiesMap);

        return factory;
    }

    // 创建连接通道
    public static Channel getchannel(String connectionmsg){
        try {
            ConnectionFactory factory = getConnectFactory();
            connect = factory.newConnection(connectionmsg);
            return connect.createChannel();
        }catch (Exception e){
            throw new RuntimeException("获取 channel 连接失败！");
        }
    }

    // 关闭连接通道
    public static void closeConnect(){
        try {
            if(connect != null){
                connect.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
