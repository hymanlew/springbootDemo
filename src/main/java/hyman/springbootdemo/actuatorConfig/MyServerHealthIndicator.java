package hyman.springbootdemo.actuatorConfig;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

// 自定义健康状态指示器，默认服务名称就是，去掉 HealthIndicator 的类名，即 MyServer。
@Component
public class MyServerHealthIndicator implements HealthIndicator{

    // 自定义检查方法
    @Override
    public Health health() {

        // 健康
        //return Health.up().withDetail("msg","服务正常！").build();
        // 异常
        return Health.down().withDetail("msg","服务异常！").build();
    }
}
