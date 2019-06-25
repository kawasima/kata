package kata.ex02.util;

import kata.ex02.model.OrderDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSender {
    private static final Logger LOG = LoggerFactory.getLogger(MailSender.class);

    public void send(OrderDetail order) {
        LOG.info("Send via Email: provider='{}'", order.getProductProvider().getName());
    }
}
