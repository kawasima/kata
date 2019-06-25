package kata.ex02.model;

public class ProductProvider {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductProvider{" +
                "name='" + name + '\'' +
                '}';
    }
}
