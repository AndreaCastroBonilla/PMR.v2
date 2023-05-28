package android.pmr;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    // ---------> DEVELOPMENT <---------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish(); // close Activity when is done
    }
}