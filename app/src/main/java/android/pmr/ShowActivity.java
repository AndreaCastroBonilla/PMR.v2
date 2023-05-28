package android.pmr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.pmr.entities.Clothes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String URL_ZARA = "https://www.zara.com/";
    public static final String URL_MASSIMODUTTI = "https://www.massimodutti.com/es/";
    public static final String URL_BERSHKA = "https://www.bershka.com/es/h-woman.html";
    public static final String URL_PULL = "https://www.pullandbear.com/";
    public static final String URL_STRA = "https://www.stradivarius.com/es/";
    public static final String URL_LEFTIES = "https://www.lefties.com/es/";
    public static final String URL_MORE= "https://www.dondeirdecompras.com/las-marcas-espanolas-de-moda-mas-destacadas/";

    private Button btnZara, btnMassimo, btnBershka, btnPull, btnStra, btnLefties, btnMore;
    private EditText txtShopName, txtDesc, txtPrice;
    private Clothes clothes;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        btnZara = findViewById(R.id.btnZara);
        btnMassimo = findViewById(R.id.btnMassimoDutti);
        btnBershka = findViewById(R.id.btnBershka);
        btnPull = findViewById(R.id.btnPull);
        btnStra = findViewById(R.id.btnStra);
        btnLefties = findViewById(R.id.btnLefties);
        btnMore = findViewById(R.id.btnMore);

        btnZara.setOnClickListener(this);
        btnMassimo.setOnClickListener(this);
        btnBershka.setOnClickListener(this);
        btnPull.setOnClickListener(this);
        btnStra.setOnClickListener(this);
        btnLefties.setOnClickListener(this);
        btnMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (v.getId()) {
            case R.id.btnZara:
                intent.setData(Uri.parse(URL_ZARA));
                startActivity(intent);
                break;
            case R.id.btnMassimoDutti:
                intent.setData(Uri.parse(URL_MASSIMODUTTI));
                startActivity(intent);
                break;
            case R.id.btnBershka:
                intent.setData(Uri.parse(URL_BERSHKA));
                startActivity(intent);
                break;
            case R.id.btnPull:
                intent.setData(Uri.parse(URL_PULL));
                startActivity(intent);
                break;
            case R.id.btnStra:
                intent.setData(Uri.parse(URL_STRA));
                startActivity(intent);
                break;
            case R.id.btnLefties:
                intent.setData(Uri.parse(URL_LEFTIES));
                startActivity(intent);
                break;
            case R.id.btnMore:
                intent.setData(Uri.parse(URL_MORE));
                startActivity(intent);
                break;
        }

    }
}