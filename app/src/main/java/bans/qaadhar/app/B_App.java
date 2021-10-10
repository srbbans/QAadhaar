package bans.qaadhar.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.database.FirebaseDatabase;

public class B_App extends Application {


    private B_App instance;
    private Context context;

    public B_App getInstance() {
        return instance;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        instance = this;
        context = this;

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }

    public void showToast(final String message) {
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show());
    }
}
