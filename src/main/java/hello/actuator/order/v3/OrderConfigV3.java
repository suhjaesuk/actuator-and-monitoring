package hello.actuator.order.v3;

import hello.actuator.order.OrderService;
import hello.actuator.order.v2.OrderServiceV2;
import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigV3 {

    @Bean
    public OrderService orderService(MeterRegistry registry) {
        return new OrderServiceV3(registry);
    }
}
