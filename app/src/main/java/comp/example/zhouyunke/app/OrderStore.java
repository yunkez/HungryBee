package comp.example.zhouyunke.app;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * class OrderCallbackImpl implements OrderCallback{
 * // implement custom callback, probably add item to list and notify adapter changes
 * }
 * OrderStore.subscribeToOrders(courierId, new OrderCallbackImpl)
 */
public class OrderStore {
    private static String FIREBASE_URL = "https://incandescent-torch-2049.firebaseio.com/";
    private final Firebase ref;
    private String key;

    public OrderStore(String key) {
        this.key = key;
        Firebase orderRef = new Firebase(FIREBASE_URL).child("couriers").child(key).child("orders");
        this.ref = orderRef;
    }

    public void subscribeToOrders(final OrderCallback callback) {
        String key = this.key;
        Firebase orderRef = new Firebase(FIREBASE_URL).child("couriers").child(key).child("orders");
        orderRef.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to the database
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Order newOrder = snapshot.getValue(Order.class);
                System.out.println(newOrder);

                // TODO implement callback in listview to get incoming orders
                callback.callback(newOrder);
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

    public void addOrder(Order order) {
        Firebase orderRef = this.ref.push();
        orderRef.setValue(order);
    }
}
