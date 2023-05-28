package android.pmr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.pmr.db.Adapter;
import android.pmr.db.DbCloset;
import android.pmr.entities.Clothes;
import android.view.MenuItem;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Activity_4_Filter extends AppCompatActivity {

    // ---------> ATTRIBUTES & CONSTANS <---------
    private final static int CONT_ACTIVIDAD = 3;

    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private ViewFlipper vf;

    private RecyclerView listClothes;
    private ArrayList<Clothes> listArrayClothes;

    // ---------> DEVELOPMENT <---------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Clothes ArrayList
        listClothes = findViewById(R.id.listClothes);
        listClothes.setLayoutManager(new LinearLayoutManager(this));

        DbCloset dbCloset = new DbCloset( Activity_4_Filter.this);
        listArrayClothes = new ArrayList<>();

        Adapter adapter = new Adapter(dbCloset.showClothes());
        listClothes.setAdapter(adapter);

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
                        sendIntent = new Intent(Activity_4_Filter.this, MainActivity.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem3:
                        // start Activity 3
                        sendIntent = new Intent(Activity_4_Filter.this, Activity_3_Wishlist.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem4:
                        // start Activity 4
                        sendIntent = new Intent(Activity_4_Filter.this, Activity_4_Filter.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem5:
                        // start Activity 5
                        sendIntent = new Intent(Activity_4_Filter.this, Activity_5_Map.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem6:
                        // start Activity 6
                        sendIntent = new Intent(Activity_4_Filter.this, Activity_6_Weather.class);
                        startActivity(sendIntent);
                        break;
                }
                // Close the navigation drawer when an item is selected
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}