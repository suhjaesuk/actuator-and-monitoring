package hello.actuator.order.v4;

import hello.actuator.order.OrderService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV4 implements OrderService {

    private AtomicInteger stock = new AtomicInteger(100);

    @Timed("my.order")
    @Override
    public void order() {

        log.info("주문");
        stock.decrementAndGet();
    }

    @Timed("my.order")
    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
