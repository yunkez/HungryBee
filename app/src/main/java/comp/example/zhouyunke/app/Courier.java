package comp.example.zhouyunke.app;

import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Courier class.
 * <p/>
 * Courier rama = new Courier();
 * rama.addRestaurants(['Indian Shop', 'Thai shop', 'KFC']);
 * String id = rama.save();
 */
public class Courier {
    private static String FIREBASE_URL = "https://incandescent-torch-2049.firebaseio.com/";

    // His current orders to update order status?
    private ArrayList<Order> orders = new ArrayList<>();
    // Restaurants to deliver from
    private ArrayList<String> restaurants = new ArrayList<>();

    public Courier() {
    }

    public Courier addRestaurants(ArrayList<String> restaurants) {
        for (String restaurant : restaurants) {
            this.restaurants.add(restaurant);
        }
        return this;
    }

    public ArrayList getOrders() {
        return orders;
    }

    /**
     * Saves the courier with the chosen restaurants.
     *
     * @return Id of the user to query the orders
     */
    public String save() {
        Firebase couriers = new Firebase(FIREBASE_URL).child("couriers");
        Firebase ref = couriers.push();
        ref.setValue(this);

        return ref.getKey();
    }
}
