import java.util.Objects;

public class Product extends Menu {
    private double price;
    private String category;
    private double optionPrice;

    public Product(String menuName, String description, double price, String category) {
        super(menuName, description);
        this.price = price;
        this.category = category;
    }
    public Product(String menuName, String description, double price, String category, double optionPrice) {
        this(menuName, description, price, category);
        this.optionPrice = optionPrice;
    }
    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }
    public double getOptionPrice() {
        return optionPrice;
    }
    @Override
    public void information() {
        System.out.printf("%-20s | W %s | %s\n ", getMenuName(), price, getDescription());
    }
    public void information(int cnt) {
        System.out.printf("%-20s | W %s | %s 개 | %s\n ", getMenuName(), price, cnt, getDescription());
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product temp = (Product) obj;
            return this.getMenuName().equals(temp.getMenuName()) &&
                    this.getPrice() == temp.getPrice() &&
                    this.getOptionPrice() == temp.getOptionPrice() &&
                    this.getDescription().equals(temp.getDescription()) &&
                    this.getCategory().equals(temp.getCategory());
        }
        return false;
    }
    public int hashCode() {
        return Objects.hash(getMenuName(), getDescription(), getPrice(), getOptionPrice(), getCategory());
    }
}
