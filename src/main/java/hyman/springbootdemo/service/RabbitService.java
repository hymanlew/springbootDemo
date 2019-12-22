package hyman.springbootdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitService {

    @RabbitListener(queues = "bootqueue")
    public void recive(Object user){
        log.info("===== "+user.toString());
        log.info("===== "+user.toString());
    }
}
