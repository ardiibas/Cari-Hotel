package id.gifood.carihotel.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import id.gifood.carihotel.R;
import id.gifood.carihotel.model.Criterias;
import id.gifood.carihotel.model.Ranges;
import id.gifood.carihotel.network.HotelService;
import id.gifood.carihotel.network.RestManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Topsis extends AppCompatActivity {

    private final String TAG = "Topsis";
    // view
    private Toolbar toolbarTopsis;
    private Spinner spinHarga, spinRating, spinJarak, spinFasilitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topsis);

        spinHarga = findViewById(R.id.spinHarga);
        spinRating = findViewById(R.id.spinRating);
        spinJarak = findViewById(R.id.spinJarak);
        spinFasilitas = findViewById(R.id.spinFasilitas);
        toolbarTopsis = findViewById(R.id.topsis_toolbar);
        setSupportActionBar(toolbarTopsis);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTopsis.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        getCriterias();
    }

    public void getCriterias(){
        HotelService api = RestManager.getClient().create(HotelService.class);
        Call<List<Criterias>> call = api.getCriterias();
        call.enqueue(new Callback<List<Criterias>>() {
            @Override
            public void onResponse(Call<List<Criterias>> call, Response<List<Criterias>> response) {
                if(response.body().get(0).getId() == 1){
                    Log.e(TAG, "onResponse: " + response.body().get(0).getRanges().get(1).getId());
                    List<Ranges> rangesList = new ArrayList<>();
                    List<String> spinHargaItem = new ArrayList<>();

                    for(int i=0; i<response.body().get(0).getRanges().size(); i++){
                        Ranges ranges = new Ranges();
                        ranges.setId(response.body().get(0).getRanges().get(i).getId());
                        ranges.setRange_start(response.body().get(0).getRanges().get(i).getRange_start());
                        ranges.setRange_end(response.body().get(0).getRanges().get(i).getRange_end());
                        if (i == (response.body().get(0).getRanges().size()-1)){
                            spinHargaItem.add(String.valueOf(ranges.getRange_start()));
                        } else {
                            spinHargaItem.add(String.valueOf(ranges.getRange_start() + " - " + ranges.getRange_end()));

                        }
//                        rangesList.add();
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, spinHargaItem);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinHarga.setAdapter(adapter);
                }

                if(response.body().get(1).getId() == 2){
                    List<String> spinJarakItem = new ArrayList<>();

                    for(int i=0; i<response.body().get(1).getRanges().size(); i++){
                        Ranges ranges = new Ranges();
                        ranges.setId(response.body().get(1).getRanges().get(i).getId());
                        ranges.setRange_start(response.body().get(1).getRanges().get(i).getRange_start());
                        ranges.setRange_end(response.body().get(1).getRanges().get(i).getRange_end());
                        if (i == (response.body().get(1).getRanges().size()-1)){
                            spinJarakItem.add(String.valueOf(ranges.getRange_start()));

                        } else {
                            spinJarakItem.add(String.valueOf(ranges.getRange_start() + " - " + ranges.getRange_end()));
                        }
//                        rangesList.add();
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, spinJarakItem);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinJarak.setAdapter(adapter);
                }

                if(response.body().get(2).getId() == 3){
                    List<String> spinRatingItem = new ArrayList<>();

                    for(int i=0; i<response.body().get(2).getRanges().size(); i++){
                        Ranges ranges = new Ranges();
                        ranges.setId(response.body().get(2).getRanges().get(i).getId());
                        ranges.setRange_start(response.body().get(2).getRanges().get(i).getRange_start());
                        ranges.setRange_end(response.body().get(2).getRanges().get(i).getRange_end());
                        if (i == (response.body().get(2).getRanges().size()-1)) {
                            spinRatingItem.add(String.valueOf(ranges.getRange_start()));
                        } else {
                            spinRatingItem.add(String.valueOf(ranges.getRange_start() + " - " + ranges.getRange_end()));
                        }
//                        rangesList.add();
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, spinRatingItem);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinRating.setAdapter(adapter);
                }

                if(response.body().get(3).getId() == 4){
                    List<String> spinFasilitasitem = new ArrayList<>();

                    for(int i=0; i<response.body().get(3).getRanges().size(); i++){
                        Ranges ranges = new Ranges();
                        ranges.setId(response.body().get(3).getRanges().get(i).getId());
                        ranges.setRange_start(response.body().get(3).getRanges().get(i).getRange_start());
                        ranges.setRange_end(response.body().get(3).getRanges().get(i).getRange_end());
                        if (i == (response.body().get(3).getRanges().size()-1)) {
                            spinFasilitasitem.add(String.valueOf(ranges.getRange_start()));
                        } else {
                            spinFasilitasitem.add(String.valueOf(ranges.getRange_start() + " - " + ranges.getRange_end()));
                        }
//                        rangesList.add();
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, spinFasilitasitem);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinFasilitas.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Criterias>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });

    }
}
