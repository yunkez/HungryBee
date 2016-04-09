package comp.example.zhouyunke.app;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by pewpew on 10/4/16.
 */
public class MainApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
