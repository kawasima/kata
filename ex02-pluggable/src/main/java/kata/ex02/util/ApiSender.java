package kata.ex02.util;

import kata.ex02.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiSender {
    private static final Logger LOG = LoggerFactory.getLogger(ApiSender.class);

    public void send(Order order) {
        LOG.info("Send via API: {}", order);
    }
}
