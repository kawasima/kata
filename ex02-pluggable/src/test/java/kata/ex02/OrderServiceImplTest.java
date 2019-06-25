package kata.ex02;

import com.google.inject.Guice;
import com.google.inject.Injector;
import kata.ex02.model.Order;
import kata.ex02.model.OrderDetail;
import kata.ex02.model.Product;
import kata.ex02.model.ProductProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;


class OrderServiceImplTest {
    final Injector injector = Guice.createInjector(new BasicModule());
    @Mock
    private Appender mockAppender;

    @Captor
    private ArgumentCaptor<LogEvent> logCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(mockAppender);
        Mockito.when(mockAppender.getName()).thenReturn("MockAppender");
        Mockito.when(mockAppender.isStarted()).thenReturn(true);
        Mockito.when(mockAppender.isStopped()).thenReturn(false);
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();

        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);

        loggerConfig.setLevel(Level.INFO);
        loggerConfig.addAppender(mockAppender, Level.INFO, null);
        ctx.updateLoggers();
    }

    @Test
    void test() {
        OrderService orderService = injector.getInstance(OrderService.class);
        Product product = new Product();
        product.setProductNo(1L);
        product.setName("カラーコーン");

        ProductProvider providerA = new ProductProvider();
        providerA.setName("A");

        ProductProvider providerB = new ProductProvider();
        providerB.setName("B");

        OrderDetail detail1 = new OrderDetail();
        detail1.setPrice(new BigInteger("3000"));
        detail1.setAmount(3);
        detail1.setProduct(product);
        detail1.setProductProvider(providerA);

        OrderDetail detail2 = new OrderDetail();
        detail2.setPrice(new BigInteger("2000"));
        detail2.setAmount(1);
        detail2.setProduct(product);
        detail2.setProductProvider(providerB);

        Order order = new Order();
        order.setOrderDetails(List.of(detail1, detail2));
        orderService.order(order);

        Mockito.verify(mockAppender, atLeast(2)).append(logCaptor.capture());

        int sentCount = 0;
        for (LogEvent logEvent : logCaptor.getAllValues()) {
            String message = logEvent.getMessage().getFormattedMessage();
            if (message.startsWith("Send via API")) {
                assertThat(message).contains("provider='A'");
                sentCount++;
            } else if (message.startsWith("Send via Email")) {
                assertThat(message).contains("provider='B'");
                sentCount++;
            }
        }
        assertThat(sentCount).isEqualTo(2);
    }

}