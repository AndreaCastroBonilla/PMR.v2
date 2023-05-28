package android.pmr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.pmr.databinding.ActivityMainBinding;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    // ---------> ATTRIBUTES & CONSTANS <---------
    ActivityMainBinding binding;
    private final static int CONT_ACTIVIDAD = 0; // activity id
    private final static int REQUEST_PERMISSION_CAMERA = 100;
    private final static int REQUEST_IMAGE_CAMERA = 101; // activity id

    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private ViewFlipper vf;

    private ImageView picture;
    private ImageButton openCamera;
    private GridView gridView;

    // ---------> DEVELOPMENT <---------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Camera component
        picture = findViewById(R.id.picture);
        openCamera = findViewById(R.id.btnOpenCamera);

        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        goToCamera();
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
                    }
                } else {
                    goToCamera();
                }
            }
        });

        // ViewFlipper component
        vf = (ViewFlipper)findViewById(R.id.viewFlipper);
        vf.setDisplayedChild(CONT_ACTIVIDAD);

        // NavigationDrawer component
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav = (NavigationView) findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent sendIntent;
                switch (item.getItemId()) {
                    case R.id.navItem1:
                        // start Activity 1
                        sendIntent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem3:
                        // start Activity 3
                        sendIntent = new Intent(MainActivity.this, Activity_3_Wishlist.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem4:
                        // start Activity 4
                        sendIntent = new Intent(MainActivity.this, Activity_4_Filter.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem5:
                        // start Activity 5
                        sendIntent = new Intent(MainActivity.this, Activity_5_Map.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem6:
                        // start Activity 6
                        sendIntent = new Intent(MainActivity.this, Activity_6_Weather.class);
                        startActivity(sendIntent);
                        break;
                }
                // Close the navigation drawer when an item is selected
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION_CAMERA) {
            if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                goToCamera();
            } else {
                Toast.makeText(this, "You need to enable permissions", Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAMERA) {
            if(resultCode == Activity.RESULT_OK) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                picture.setImageBitmap(bitmap);
               // GridAdapter gridAdapter = new GridAdapter(MainActivity.this, bitmap);
                // gridView.setAdapter(gridAdapter);
                Log.i("TAG", "rasult:" + bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void goToCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // draw interface

        if(cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAMERA);
        }
    }
}