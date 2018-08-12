package id.gifood.carihotel.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.gifood.carihotel.R;
import id.gifood.carihotel.adapter.AdapterTopsis;
import id.gifood.carihotel.model.topsis.TopsisModel;

public class HasilPencarian extends AppCompatActivity {

    private List<TopsisModel> topsisModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterTopsis adapterTopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_pencarian);

        recyclerView = findViewById(R.id.bottom_sheet_recycler);

        adapterTopsis = new AdapterTopsis(getApplicationContext(), topsisModelList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
