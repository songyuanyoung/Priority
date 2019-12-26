import android.app.Application;
import android.os.Debug;

import com.example.priority.BuildConfig;

import timber.log.Timber;

public class MyToDoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }
}
