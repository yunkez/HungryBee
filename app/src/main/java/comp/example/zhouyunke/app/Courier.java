package comp.example.zhouyunke.app;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Courier class.
 * <p/>
 * Courier rama = new Courier();
 * rama.addRestaurants(['Indian Shop', 'Thai shop', 'KFC']);
 * String id = rama.save();
 */
public class Courier {
    private static String FIREBASE_URL = "https://incandescent-torch-2049.firebaseio.com/";
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


    /**
     * Saves the courier with the chosen restaurants.
     *
     * @return Id of the user to query the orders
     */
    public String save() {
        Firebase couriers = new Firebase(FIREBASE_URL).child("couriers");
        Firebase ref = couriers.push();
        HashMap<String, ArrayList> mapping = new HashMap<>();
        mapping.put("restaurants", this.restaurants);
        ref.setValue(mapping);

        return ref.getKey();
    }
}
