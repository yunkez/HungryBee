package comp.example.zhouyunke.app;

/**
 * Created by pewpew on 10/4/16.
 */
public class Order {
    private String name;

    private int quantity;
    private String status;
    private static String ORDER_PLACED = "ORDER_PLACED";

    public Order() {
    }

    public Order(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.status = ORDER_PLACED;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void save() {
        // TODO save to firebase
    }

    @Override
    public String toString() {
        return "New Order: " + this.getName() + "(" + this.getStatus() + ")" + " x " + this.getQuantity();
    }
}
