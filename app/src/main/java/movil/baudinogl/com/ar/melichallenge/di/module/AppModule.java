package movil.baudinogl.com.ar.melichallenge.di.module;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import movil.baudinogl.com.ar.melichallenge.synchronization.model.ResponseSearch;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return application;
    }

    @Singleton
    @Provides
    Context providesApplicationContext() {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    RequestQueue providesRequestQueue() {
        return Volley.newRequestQueue(application.getApplicationContext());
    }

    @Singleton
    @Provides
    Gson providesGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                .create();
    }

    @Singleton
    @Provides
    MutableLiveData<ResponseSearch> providesMutableResponseSearch() {
        return new MutableLiveData<>();
    }
}