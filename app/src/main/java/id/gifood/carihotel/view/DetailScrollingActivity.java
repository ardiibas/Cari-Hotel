package id.gifood.carihotel.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.gifood.carihotel.R;

public class DetailScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String nama_hotel = getIntent().getStringExtra("nama");
        String alamat_hotel = getIntent().getStringExtra("alamat");
        String url_hotel = getIntent().getStringExtra("image");
/*        ArrayList<String> fasilitas = getIntent().getStringArrayListExtra("fasilitas");

        Log.i("TAG", fasilitas.toString());*/

        toolbar.setTitle(nama_hotel);

        TextView textView = findViewById(R.id.detail_alamat_hotel);
        textView.setText(alamat_hotel);

/*        ListView listView = findViewById(R.id.list_detail);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, fasilitas
        );

        listView.setAdapter(stringArrayAdapter);*/

        ImageView imageView = findViewById(R.id.detail_image);
        Glide.with(getApplicationContext())
                .load(url_hotel).into(imageView);
    }
}
