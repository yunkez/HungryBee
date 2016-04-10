package comp.example.zhouyunke.app;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

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

    private void subscribeToOrders(String key) {
        Firebase orderRef = new Firebase(FIREBASE_URL).child("couriers").child(key).child("orders");
        final ArrayList<Order> orderList = this.orders;
        orderRef.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to the database
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Order newOrder = snapshot.getValue(Order.class);
                System.out.println(newOrder);
                orderList.add(newOrder);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // SCREW YOU DONT DO THIS
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // SCREW YOU DONT DO THIS
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                // SCREW YOU DONT DO THIS
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // SCREW YOU DONT DO THIS
            }
        });
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
        String key = ref.getKey();

        // Subscribe to orders so we can get orders later. is this efficient? probably not
        subscribeToOrders(key);
        return key;
    }
}
