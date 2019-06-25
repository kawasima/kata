package kata.ex02.plugin;

import com.google.inject.Inject;
import kata.ex02.Plugin;
import kata.ex02.model.Order;
import kata.ex02.model.OrderDetail;
import kata.ex02.model.ProductProvider;
import kata.ex02.util.MailSender;

import java.util.Optional;

public class MailSenderPlugin implements Plugin<Order> {
    private final MailSender mailSender;

    @Inject
    public MailSenderPlugin(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void run(Order order) {
        order.getOrderDetails().forEach(detail -> Optional
                .ofNullable(detail.getProductProvider())
                .map(ProductProvider::getName)
                .filter(name -> name.equals("B"))
                .ifPresent(n -> mailSender.send(detail)));
    }
}
