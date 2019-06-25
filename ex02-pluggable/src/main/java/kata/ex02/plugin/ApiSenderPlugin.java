package kata.ex02.plugin;

import com.google.inject.Inject;
import kata.ex02.Plugin;
import kata.ex02.model.Order;
import kata.ex02.model.ProductProvider;
import kata.ex02.util.ApiSender;

import java.util.Optional;

public class ApiSenderPlugin implements Plugin<Order> {
    private final ApiSender apiSender;

    @Inject
    public ApiSenderPlugin(ApiSender apiSender) {
        this.apiSender = apiSender;
    }

    @Override
    public void run(Order order) {
        order.getOrderDetails().forEach(detail -> Optional
                .ofNullable(detail.getProductProvider())
                .map(ProductProvider::getName)
                .filter(name -> name.equals("A"))
                .ifPresent(n -> apiSender.send(detail)));
    }
}
