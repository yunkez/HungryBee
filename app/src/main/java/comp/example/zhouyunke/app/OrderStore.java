package comp.example.zhouyunke.app;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class OrderStore {
    private static String FIREBASE_URL = "https://incandescent-torch-2049.firebaseio.com/";

    public static void subscribeToOrders(String key, final OrderCallback callback) {
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
}
