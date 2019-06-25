package kata.ex02;

import com.google.inject.AbstractModule;
import kata.ex02.repository.OrderRepository;
import kata.ex02.repository.OrderRepositoryImpl;
import kata.ex02.util.ApiSender;
import kata.ex02.util.MailSender;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(OrderRepository.class).to(OrderRepositoryImpl.class);
        bind(OrderService.class).to(OrderServiceImpl.class);
        bind(ApiSender.class);
        bind(MailSender.class);
        bind(PluginRegistry.class);
    }
}
