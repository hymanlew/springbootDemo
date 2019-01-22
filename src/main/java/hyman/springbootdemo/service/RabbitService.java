package hyman.springbootdemo.service;

import hyman.springbootdemo.entity.User;
import hyman.springbootdemo.util.Logutil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @RabbitListener(queues = "bootqueue")
    public void recive(Object user){
        Logutil.logger.info("===== "+user.toString());
        Logutil.logger.info("===== "+user.toString());
    }
}
