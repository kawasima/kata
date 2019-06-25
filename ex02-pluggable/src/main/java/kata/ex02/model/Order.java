package kata.ex02.model;

import java.util.List;

public class Order {
    private Long orderNo;
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Long getOrderNo() {
        return this.orderNo;
    }

    public List<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String toString() {
        return "Order(orderNo=" + this.getOrderNo() + ", orderDetails=" + this.getOrderDetails() + ")";
    }
}
