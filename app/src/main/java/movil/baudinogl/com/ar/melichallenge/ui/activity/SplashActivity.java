package movil.baudinogl.com.ar.melichallenge.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import movil.baudinogl.com.ar.melichallenge.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed((Runnable) () -> {
            Intent mainIntent = new Intent(this, SearchActivity.class);
            startActivity(mainIntent);
            finish();
        }, 1000L);
    }
}