package kata.ex02;

import com.google.inject.Inject;
import kata.ex02.model.Order;
import kata.ex02.repository.OrderRepository;
import kata.ex02.util.ApiSender;
import kata.ex02.util.MailSender;

import static kata.ex02.HookPoint.AFTER_ORDER_SAVE;
import static kata.ex02.HookPoint.BEFORE_ORDER_SAVE;

public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;
    private PluginRegistry pluginRegistry;

    @Inject
    public OrderServiceImpl(OrderRepository repository, PluginRegistry pluginRegistry) {
        this.repository = repository;
        this.pluginRegistry = pluginRegistry;
    }

    @Override
    public void order(Order order) {
        pluginRegistry.runPlugins(BEFORE_ORDER_SAVE, order);
        repository.save(order);
        pluginRegistry.runPlugins(AFTER_ORDER_SAVE, order);
    }
}
