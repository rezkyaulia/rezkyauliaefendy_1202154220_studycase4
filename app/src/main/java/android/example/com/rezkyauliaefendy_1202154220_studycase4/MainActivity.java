package android.example.com.rezkyauliaefendy_1202154220_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnmaha, btncari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnmaha = (Button) findViewById(R.id.mahasiswa);
        btncari = (Button) findViewById(R.id.cari);
    }


    public void klikmaha(View view) {
        Intent intent= new Intent(MainActivity.this,ListMahasiswa.class);
        startActivity(intent);
    }

    public void cari(View view) {
        Intent intent= new Intent(MainActivity.this,PencariGambar.class);
        startActivity(intent);
    }
}
