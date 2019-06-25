package kata.ex02;

import com.google.inject.Inject;
import kata.ex02.model.Order;
import kata.ex02.repository.OrderRepository;
import kata.ex02.util.ApiSender;
import kata.ex02.util.MailSender;

public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;
    private ApiSender apiSender;
    private MailSender mailSender;

    @Inject
    public OrderServiceImpl(OrderRepository repository, ApiSender apiSender, MailSender mailSender) {
        this.repository = repository;
        this.apiSender = apiSender;
        this.mailSender = mailSender;
    }

    @Override
    public void order(Order order) {
        repository.save(order);
    }
}
