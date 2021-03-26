package movil.baudinogl.com.ar.melichallenge.core;

import android.app.Application;
import android.os.StrictMode;

import movil.baudinogl.com.ar.melichallenge.di.component.AppComponent;
import movil.baudinogl.com.ar.melichallenge.di.component.DaggerAppComponent;
import movil.baudinogl.com.ar.melichallenge.di.module.AppModule;

public class App extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
