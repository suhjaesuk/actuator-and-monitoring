package hello.actuator;

import hello.actuator.order.guage.StockConfigV1;
import hello.actuator.order.guage.StockConfigV2;
import hello.actuator.order.v0.OrderConfigV0;
import hello.actuator.order.v1.OrderConfigV1;
import hello.actuator.order.v2.OrderConfigV2;
import hello.actuator.order.v3.OrderConfigV3;
import hello.actuator.order.v4.OrderConfigV4;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import({OrderConfigV4.class, StockConfigV2.class})
@SpringBootApplication(scanBasePackages = "hello.actuator.controller")
public class ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

    @Bean
    public InMemoryHttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }
}
