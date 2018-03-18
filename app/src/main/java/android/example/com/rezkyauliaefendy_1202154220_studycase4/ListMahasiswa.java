package android.example.com.rezkyauliaefendy_1202154220_studycase4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListMahasiswa extends AppCompatActivity {
    ListView listView;
    //array yang berisikan nama mahasiswa
    private String[] nama = {"Abi", "Anto","Bella", "Budi", "Cantika", "Chika", "Ferry", "Gandi", "Joni", "Kemal"
            , "Rozi", "Tamias","Ucup", "Wujud" };
    Button btnmulai;
    private static Parcelable mListViewScroll = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);
        //menginisiasi
        listView = (ListView) findViewById(R.id.listnama);
        btnmulai = (Button) findViewById(R.id.button);
    }

    public void start(View view) {
        //mengeset array adapter
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));

        if (mListViewScroll!=null){
            listView.onRestoreInstanceState(mListViewScroll);
        }
        //mengeset ketika di klik akan menjalankan code setelah nya
        btnmulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new mytask().execute();
            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // menyimpan listview
        mListViewScroll = listView.onSaveInstanceState();
    }

    private class mytask extends AsyncTask<Void,String,String> {
        ArrayAdapter<String> adapter;
        ProgressDialog progressdialog;
        int count;

        @Override
        protected void onPreExecute() {
            //mengambil data dari array yang telah dibuat
            adapter = (ArrayAdapter<String>)listView.getAdapter();

            //membuat object progress dialog
            progressdialog = new ProgressDialog(ListMahasiswa.this);
            //mengeset title progress dialog
            progressdialog.setTitle("Loading Data");
            progressdialog.setProgressStyle(progressdialog.STYLE_HORIZONTAL);
            progressdialog.setMax(15);
            progressdialog.setProgress(0);
            //menampilkan progress dialog
            progressdialog.show();
            //memastikan bahwa hitungan sebelum di eksekusi adalah 0
            count = 0;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //membuat perulangan untuk memunculkan nama mahasiswa
            for (String listnama : nama){
                publishProgress(listnama);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //mengembalikan nilai dengan memunculkan Toast
            return "List Nama Mahasiswa";
        }

        //berguna untuk memperbarui di dalam UI
        @Override
        protected void onProgressUpdate(String... values) {
            //array dimulai dari 0
            adapter.add(values[0]);
            //hitungan pada saat progress update bertambah
            count++;
            //mengeset hitungan di dalam progress dialog
            progressdialog.setProgress(count);
        }
        @Override
        protected void onPostExecute(String result) {
            //menampilkan nilai dari return yang ada di method doInBackground
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            //setelah loading progress selesai, maka akan muncul List Nama Mahasiswa
            progressdialog.hide();


        }



    }
}
