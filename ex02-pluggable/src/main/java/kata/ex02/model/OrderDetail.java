package kata.ex02.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
@ToString
public class OrderDetail {
    private Order order;
    private Product product;
    private BigInteger price;
    private long amount;
}
