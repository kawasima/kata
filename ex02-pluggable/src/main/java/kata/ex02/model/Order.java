package kata.ex02.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Order {
    private Long orderNo;
    private List<OrderDetail> orderDetails;
}
