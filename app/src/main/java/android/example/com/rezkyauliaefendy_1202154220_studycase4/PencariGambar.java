package android.example.com.rezkyauliaefendy_1202154220_studycase4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class PencariGambar extends AppCompatActivity {
    Button cariG;
    ImageView gambar;
    EditText isiURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);
        //menginisiasi
        cariG = (Button) findViewById(R.id.cari);
        gambar = (ImageView) findViewById(R.id.image);
        isiURL = (EditText) findViewById(R.id.text3);
    }

    //ketika Button di klik maka akan menjalankan fungsi berikut
    public void cariGambar(View view) {
        String URLG = isiURL.getText().toString();
        new DownloadImage().execute(URLG);
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        //berguna untuk menyiapkan Tugas yang dibuat
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        //berguna untuk mengimplementasikan dan mengeksekusi
        @Override
        protected Bitmap doInBackground(String... voids) {

            String imageURL = voids[0];

            Bitmap bitmap = null;
            try {
                // mendownload gambar dari url
                InputStream input = new java.net.URL(imageURL).openStream();
                // merubah input url ke bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        //memperbarui hasil ke UI setelah AsyncTask selesai di tampilkan
        @Override
        protected void onPostExecute(Bitmap result) {
            // mengeset bitmap ke dalam imageView yang sudah di sediakan
            gambar.setImageBitmap(result);

        }
    }
}