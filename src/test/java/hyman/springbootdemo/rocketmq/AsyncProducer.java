package hyman.springbootdemo.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 异步
 *
 * @description nohup sh bin/mqbroker -n 192.168.11.230:9876 autoCreateTopicEnable=true > logs/broker.log 2>&1 &
 * @description nohup sh bin/mqnamesrv > logs/mqnamesrv.log 2>&1 &
 * @author huaimin
 */
public class AsyncProducer {

    /**
     * @param args
     * @throws UnsupportedEncodingException
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws UnsupportedEncodingException, MQClientException, RemotingException, InterruptedException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("MY_GROUP");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance.
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 100; i++) {
            final int index = i;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest",
                    "TagA",
                    "OrderID188"+i,
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());
                }
                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        //Shut down once the producer instance is not longer in use.
        //producer.shutdown();  //注意异步的时候不要去关闭
    }
}
