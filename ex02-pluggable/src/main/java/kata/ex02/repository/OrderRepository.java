package kata.ex02.repository;

import kata.ex02.model.Order;

public interface OrderRepository {
    void save(Order order);
}
