package hello.actuator.order.v4;

import hello.actuator.order.OrderService;
import hello.actuator.order.v3.OrderServiceV3;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigV4 {

    @Bean
    public OrderService orderService( ) {
        return new OrderServiceV4();
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
