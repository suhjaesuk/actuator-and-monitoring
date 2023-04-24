package hello.actuator.controller;

import hello.actuator.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 예제의 단순함을 위해 GET 메서드 사용
     */
    @GetMapping("/order")
    public String order() {
        log.info("order");
        orderService.order();
        return "order";
    }

    @GetMapping("/cancel")
    public String cancel() {
        log.info("cancel");
        orderService.cancel();
        return "cancel";
    }

    @GetMapping("/stock")
    public int stock() {
        log.info("stock");
        return orderService.getStock().get();
    }
}
