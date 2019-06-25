package kata.ex02.model;

public class Product {
    private Long productNo;
    private String name;

    public Long getProductNo() {
        return this.productNo;
    }

    public String getName() {
        return this.name;
    }

    public void setProductNo(Long productNo) {
        this.productNo = productNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Product(productNo=" + this.getProductNo() + ", name=" + this.getName() + ")";
    }
}
