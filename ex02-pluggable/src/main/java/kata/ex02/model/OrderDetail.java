package kata.ex02.model;

import java.math.BigInteger;

public class OrderDetail {
    private Order order;
    private Product product;
    private ProductProvider productProvider;
    private BigInteger price;
    private long amount;

    public OrderDetail() {
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductProvider getProductProvider() {
        return productProvider;
    }

    public void setProductProvider(ProductProvider productProvider) {
        this.productProvider = productProvider;
    }

    public BigInteger getPrice() {
        return this.price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "order=" + order +
                ", product=" + product +
                ", productProvider=" + productProvider +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
