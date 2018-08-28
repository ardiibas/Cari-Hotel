package id.gifood.carihotel.view;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.gifood.carihotel.R;
import id.gifood.carihotel.adapter.Adapter;
import id.gifood.carihotel.adapter.AdapterTopsis;
import id.gifood.carihotel.adapter.FacilityAdapter;
import id.gifood.carihotel.fragment.FragmentMaps;
import id.gifood.carihotel.model.list.Criterias;
import id.gifood.carihotel.model.list.DataHotels;
import id.gifood.carihotel.model.list.Facility;
import id.gifood.carihotel.model.list.Ranges;
import id.gifood.carihotel.model.topsis.TopsisModel;
import id.gifood.carihotel.network.HotelService;
import id.gifood.carihotel.network.RestManager;
import id.gifood.carihotel.util.ItemClickList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Topsis extends AppCompatActivity {

    private final String TAG = "TopsisModel";
    // view
    private Toolbar toolbarTopsis;
    private Spinner spinHarga, spinRating, spinJarak, spinFasilitas;
    private Button mBtnCari;
    private RecyclerView mRecycleFacility;
    private List<Facility> mFacilitySelected = new ArrayList<>();

    private List<TopsisModel> topsisModels = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterTopsis adapter;

    private List<String> fasilitas = new ArrayList<String>();
    private List<String> sekitar = new ArrayList<String>();

    private LinearLayout layoutTopsis, layoutBottomSheet;
    private BottomSheetBehavior sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topsis);

        recyclerView = findViewById(R.id.bottom_sheet_recycler);

        adapter = new AdapterTopsis(getApplicationContext(), topsisModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        layoutBottomSheet = findViewById(R.id.bottom_sheet_layout);
        layoutTopsis = findViewById(R.id.topsis_layout);

        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {

                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;

                    case BottomSheetBehavior.STATE_EXPANDED: {
                    }
                    break;

                    case BottomSheetBehavior.STATE_COLLAPSED: {
                    }
                    break;

                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;

                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        spinHarga = findViewById(R.id.spinHarga);
        spinRating = findViewById(R.id.spinRating);
        spinJarak = findViewById(R.id.spinJarak);
        spinFasilitas = findViewById(R.id.spinFasilitas);
        mRecycleFacility = findViewById(R.id.topsis_list_item);
        toolbarTopsis = findViewById(R.id.topsis_toolbar);
        mBtnCari = findViewById(R.id.topsis_button_cari);
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

        getFacilities();

        initialize();

        recyclerView.addOnItemTouchListener(
                new ItemClickList(getApplicationContext(), new ItemClickList.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(Topsis.this, DetailScrollingActivity.class);

                        intent.putExtra("nama", topsisModels.get(position).getName());
                        intent.putExtra("image", topsisModels.get(position).getImages().get(0));
                        intent.putExtra("alamat", topsisModels.get(position).getAddress());

                        for (int i = 0; i < topsisModels.get(position).getFacilities().size(); i++) {
                            fasilitas.add(topsisModels.get(position).getFacilities().get(i));
                        }
                        intent.putExtra("fasilitas", (ArrayList<String>) fasilitas);

                        for (int i = 0; i < topsisModels.get(position).getArounds().size(); i++) {
                            sekitar.add(topsisModels.get(position).getArounds().get(i));
                        }
                        intent.putExtra("sekitar", (ArrayList<String>) sekitar);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                })
        );
    }

    private void deleteFacility(Facility facility) {
        mFacilitySelected.remove(facility);
    }

    private void addFacility(Facility facility) {
        Integer index = mFacilitySelected.indexOf(facility);

        if (index == -1) {
            mFacilitySelected.add(facility);
        }
    }

    private void initialize() {
        mRecycleFacility.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false));

        mRecycleFacility.setAdapter(new FacilityAdapter(new FacilityAdapter.Listener() {
            @Override
            public void onClick(Facility facility, Boolean isChecked) {
                if (isChecked) {
                    addFacility(facility);
                } else {
                    deleteFacility(facility);
                }
            }
        }));

        mBtnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Touch me senpai!");
                int price = ((Ranges) spinHarga.getSelectedItem()).getId();
                int distance = ((Ranges) spinJarak.getSelectedItem()).getId();
                int rating = ((Ranges) spinRating.getSelectedItem()).getId();
                int facility = ((Ranges) spinFasilitas.getSelectedItem()).getId();
                getPredict(price, distance, rating, facility, mFacilitySelected, FragmentMaps.lat, FragmentMaps.lng);
            }
        });
    }

    public void getPredict(int price,
                           int distance,
                           int rating,
                           int facility,
                           List<Facility> lFacility,
                           double latitude,
                           double longitude) {
        //Map<String , Map<String, String>> query = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        data.put("criteria[harga]", String.valueOf(price));
        data.put("criteria[jarak]", String.valueOf(distance));
        data.put("criteria[rating]", String.valueOf(rating));
        data.put("criteria[fasilitas]", String.valueOf(facility));
        data.put("location[lat]", String.valueOf(latitude));
        data.put("location[lon]", String.valueOf(longitude));

        //Validasi
        if (lFacility.size() > 0) {
            data = getFacilitiesString(lFacility, data);

            HotelService api = RestManager.getClient().create(HotelService.class);
            Call<List<TopsisModel>> call = api.getHotelResultsList(data);
            call.enqueue(new Callback<List<TopsisModel>>() {
                @Override
                public void onResponse(Call<List<TopsisModel>> call, Response<List<TopsisModel>> response) {
                    for (int i = 0; i < response.body().size(); i++) {
                        topsisModels.add(response.body().get(i));
                    }

    /*                Intent intent = new Intent(Topsis.this, HasilPencarian.class);
                    intent.putExtra("data", (Parcelable) topsisModels);*/

                    adapter = new AdapterTopsis(getApplicationContext(), topsisModels);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                    toggleBottomSheet(); //menampilkan list
                    Log.i("Topsis", "Halo " + response.body().toString());

                    //                startActivity(intent);
                }

                @Override
                public void onFailure(Call<List<TopsisModel>> call, Throwable t) {
                    Log.e(TAG, "Check me senpai!" + t.getMessage());
                }
            });

        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Peringatan")
                    .setMessage("Pilih salah satu Fasilitas untuk melanjutkan pencarian!")
                    .setPositiveButton("Ok", null)
                    .show();
        }

        Log.i("Topsis", "Data " + data.toString());

/*        HotelService api = RestManager.getClient().create(HotelService.class);
        Call<JsonObject> call = api.getHotelResults(data);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
*//*                adapter = new Adapter(getApplicationContext(), response.body());

                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                toggleBottomSheet();*//* //menampilkan list
                Log.i("Topsis", "Halo " + response.body().toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "Check me senpai!" + t.getMessage());
            }
        });*/

//        HotelService api = RestManager.getClient().create(HotelService.class);
//        Call<JsonObject> call = api.getHotelResults(data);
//        call.enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                List<DataHotels> lDataHotels = new ArrayList<>();
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response.body().toString());
//                    JSONArray jsonArray = jsonObject.
//                    int index = 0;
//                    while (true){
//                        try {
//                            Gson gson = new Gson();
//                            String json = jsonObject.get(String.valueOf(index)).toString();
//                            DataHotels data = gson.fromJson(json, DataHotels.class);
//                            lDataHotels.add(data);
//                            index++;
//                        }catch (Exception ex){
//                            Log.e(TAG, "Check me senpai!");
//                            break;
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//                adapter = new Adapter(getApplicationContext(), lDataHotels);
//                adapter.notifyDataSetChanged();
//                recyclerView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                Log.e(TAG, "Check me senpai!");
//            }
//        });

//        HotelService api = RestManager.getClient().create(HotelService.class);
//        Call<Hotels> call = api.getHotelResultss(data);
//        call.enqueue(new Callback<Hotels>() {
//            @Override
//            public void onResponse(Call<Hotels> call, Response<Hotels> response) {
//                dataHotelsList = response.body().getData();
//
//                if (dataHotelsList != null) {
//                    adapter = new Adapter(getApplicationContext(), dataHotelsList);
//                    adapter.notifyDataSetChanged();
//                    recyclerView.setAdapter(adapter);
//                }
//
//                toggleBottomSheet();
//
//                Log.d(TAG, "Check the values senpai! You can do that" + response.body().getData());
//            }
//
//            @Override
//            public void onFailure(Call<Hotels> call, Throwable t) {
//                Log.e(TAG, "Check me senpai!" + t.getMessage());
//            }
//        });
    }

    public void getFacilities() {
        HotelService api = RestManager.getClient().create(HotelService.class);
        Call<List<Facility>> call = api.getFacilities();
        call.enqueue(new Callback<List<Facility>>() {
            @Override
            public void onResponse(Call<List<Facility>> call, Response<List<Facility>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    ((FacilityAdapter) mRecycleFacility.getAdapter()).add(response.body().get(i));
                }
            }

            @Override
            public void onFailure(Call<List<Facility>> call, Throwable t) {
                Log.e(TAG, "Check me senpai!");
            }
        });

    }

    private Map<String, String> getFacilitiesString(List<Facility> data, Map<String, String> mData) {
        for (int i = 0; i < data.size(); i++) {
            mData.put("facilities[" + String.valueOf(i) + "]", String.valueOf(data.get(i).getId()));
        }
        return mData;
    }

    public void getCriterias() {
        HotelService api = RestManager.getClient().create(HotelService.class);
        Call<List<Criterias>> call = api.getCriterias();
        call.enqueue(new Callback<List<Criterias>>() {
            @Override
            public void onResponse(Call<List<Criterias>> call, Response<List<Criterias>> response) {
                if (response.body().get(0).getId() == 1) {
                    Log.e(TAG, "onResponse: " + response.body().get(0).getRanges().get(1).getId());
                    List<Ranges> rangesList = new ArrayList<>();
                    List<Ranges> spinHargaItem = new ArrayList<>();

                    for (int i = 0; i < response.body().get(0).getRanges().size(); i++) {
                        Ranges ranges = new Ranges();
                        ranges.setId(response.body().get(0).getRanges().get(i).getId());
                        ranges.setRange_start(response.body().get(0).getRanges().get(i).getRange_start());
                        ranges.setRange_end(response.body().get(0).getRanges().get(i).getRange_end());
                        if (i == (response.body().get(0).getRanges().size() - 1)) {
                            //spinHargaItem.add(String.valueOf(ranges.getRange_start()));
                        } else {
                            //spinHargaItem.add(String.valueOf(ranges.getRange_start() + " - " + ranges.getRange_end()));

                        }
                        spinHargaItem.add(ranges);
//                        rangesList.add();
                    }

                    ArrayAdapter<Ranges> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, spinHargaItem);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinHarga.setAdapter(adapter);
                }

                if (response.body().get(1).getId() == 2) {
                    List<Ranges> spinJarakItem = new ArrayList<>();

                    for (int i = 0; i < response.body().get(1).getRanges().size(); i++) {
                        Ranges ranges = new Ranges();
                        ranges.setId(response.body().get(1).getRanges().get(i).getId());
                        ranges.setRange_start(response.body().get(1).getRanges().get(i).getRange_start());
                        ranges.setRange_end(response.body().get(1).getRanges().get(i).getRange_end());
                        if (i == (response.body().get(1).getRanges().size() - 1)) {
                            //spinJarakItem.add(String.valueOf(ranges.getRange_start()));

                        } else {
                            //spinJarakItem.add(String.valueOf(ranges.getRange_start() + " - " + ranges.getRange_end()));
                        }
                        spinJarakItem.add(ranges);
//                        rangesList.add();
                    }

                    ArrayAdapter<Ranges> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, spinJarakItem);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinJarak.setAdapter(adapter);
                }

                if (response.body().get(2).getId() == 3) {
                    List<Ranges> spinRatingItem = new ArrayList<>();

                    for (int i = 0; i < response.body().get(2).getRanges().size(); i++) {
                        Ranges ranges = new Ranges();
                        ranges.setId(response.body().get(2).getRanges().get(i).getId());
                        ranges.setRange_start(response.body().get(2).getRanges().get(i).getRange_start());
                        ranges.setRange_end(response.body().get(2).getRanges().get(i).getRange_end());
                        if (i == (response.body().get(2).getRanges().size() - 1)) {
                            //spinRatingItem.add(String.valueOf(ranges.getRange_start()));
                        } else {
                            //spinRatingItem.add(String.valueOf(ranges.getRange_start() + " - " + ranges.getRange_end()));
                        }
                        spinRatingItem.add(ranges);
//                        rangesList.add();
                    }

                    ArrayAdapter<Ranges> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, spinRatingItem);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinRating.setAdapter(adapter);
                }

                if (response.body().get(3).getId() == 4) {
                    List<Ranges> spinFasilitasitem = new ArrayList<>();

                    for (int i = 0; i < response.body().get(3).getRanges().size(); i++) {
                        Ranges ranges = new Ranges();
                        ranges.setId(response.body().get(3).getRanges().get(i).getId());
                        ranges.setRange_start(response.body().get(3).getRanges().get(i).getRange_start());
                        ranges.setRange_end(response.body().get(3).getRanges().get(i).getRange_end());
                        if (i == (response.body().get(3).getRanges().size() - 1)) {
                            //spinFasilitasitem.add(String.valueOf(ranges.getRange_start()));
                        } else {
                            //spinFasilitasitem.add(String.valueOf(ranges.getRange_start() + " - " + ranges.getRange_end()));
                        }
                        spinFasilitasitem.add(ranges);
//                        rangesList.add();
                    }

                    ArrayAdapter<Ranges> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, spinFasilitasitem);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinFasilitas.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Criterias>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void toggleBottomSheet() {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            layoutTopsis.setAlpha(Float.parseFloat("0.5"));
            spinHarga.setEnabled(false);
            spinRating.setEnabled(false);
            spinJarak.setEnabled(false);
            spinFasilitas.setEnabled(false);

        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            layoutTopsis.setAlpha(Float.parseFloat("1.0"));
            spinHarga.setEnabled(true);
            spinRating.setEnabled(true);
            spinJarak.setEnabled(true);
            spinFasilitas.setEnabled(true);
        }
    }

}
