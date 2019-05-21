package kata.ex02.util;

import kata.ex02.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSender {
    private static final Logger LOG = LoggerFactory.getLogger(MailSender.class);

    public void send(Order order) {
        LOG.info("Send via Email: {}", order);
    }
}
