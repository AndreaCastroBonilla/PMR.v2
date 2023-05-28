package android.pmr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.pmr.db.DbCloset;
import android.pmr.db.DbHelper;
import android.pmr.entities.Clothes;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Activity_3_Wishlist extends AppCompatActivity {

    // ---------> ATTRIBUTES & CONSTANS <---------
    private final static int CONT_ACTIVIDAD = 2;

    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private ViewFlipper vf;

    private Button btnCrear, btnSave;
    private EditText txtShopName, txtDesc, txtPrice;

    // ---------> DEVELOPMENT <---------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /*btnCrear = findViewById(R.id.button);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(Activity_3_Wishlist.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db!=null){
                    Toast.makeText(Activity_3_Wishlist.this, "base de datos creada", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Activity_3_Wishlist.this, "error", Toast.LENGTH_SHORT).show();
                }

            }
        });*/

        txtShopName = findViewById(R.id.txtShopName);
        txtDesc = findViewById(R.id.txtDesc);
        txtPrice = findViewById(R.id.txtPrice);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbCloset dbCloset = new DbCloset(Activity_3_Wishlist.this);
                long id = dbCloset.insertClothing(txtShopName.getText().toString(), txtDesc.getText().toString(), txtPrice.getText().toString());

                if(txtShopName.getText().toString().isEmpty()) {
                    Toast.makeText(Activity_3_Wishlist.this,"ERROR: Shop name empty \uD83D\uDE41", Toast.LENGTH_LONG).show();
                } else if(txtDesc.getText().toString().isEmpty()) {
                    Toast.makeText(Activity_3_Wishlist.this,"ERROR: Description empty \uD83D\uDE41", Toast.LENGTH_LONG).show();
                } else if(txtPrice.getText().toString().isEmpty()) {
                    Toast.makeText(Activity_3_Wishlist.this,"ERROR: Price empty \uD83D\uDE41", Toast.LENGTH_LONG).show();
                } else {
                    if(id > 0) {
                        Toast.makeText(Activity_3_Wishlist.this,"SAVED \uD83D\uDE43", Toast.LENGTH_LONG).show();
                        clean();
                    } else {
                        Toast.makeText(Activity_3_Wishlist.this,"ERROR \uD83D\uDE41", Toast.LENGTH_LONG).show();
                    }
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
                        sendIntent = new Intent(Activity_3_Wishlist.this, MainActivity.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem3:
                        // start Activity 3
                        sendIntent = new Intent(Activity_3_Wishlist.this, Activity_3_Wishlist.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem4:
                        // start Activity 4
                        sendIntent = new Intent(Activity_3_Wishlist.this, Activity_4_Filter.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem5:
                        // start Activity 5
                        sendIntent = new Intent(Activity_3_Wishlist.this, Activity_5_Map.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.navItem6:
                        // start Activity 6
                        sendIntent = new Intent(Activity_3_Wishlist.this, Activity_6_Weather.class);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void nuevoRegistro() {
    }

    private void clean(){
        txtShopName.setText("");
        txtDesc.setText("");
        txtPrice.setText("");
    }
}