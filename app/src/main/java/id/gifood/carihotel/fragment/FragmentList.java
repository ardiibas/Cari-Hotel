package id.gifood.carihotel.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import id.gifood.carihotel.R;
import id.gifood.carihotel.adapter.Adapter;
import id.gifood.carihotel.model.list.Hotels;
import id.gifood.carihotel.model.list.DataHotels;
import id.gifood.carihotel.network.HotelService;
import id.gifood.carihotel.network.RestManager;
import id.gifood.carihotel.util.ItemClickList;
import id.gifood.carihotel.view.DetailScrollingActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ibas on 21/03/2018.
 */

public class FragmentList extends Fragment implements MaterialSearchBar.OnSearchActionListener, TextWatcher {

    private OnFragmentInteractionListener listener;

    private List<DataHotels> dataHotelsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter adapter;

    private HotelService api;

    private MaterialSearchBar searchBar;

    private List<String> fasilitas = new ArrayList<String>();
    private List<String> sekitar = new ArrayList<String>();

    public static FragmentList newInstance() {
        return new FragmentList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.rvHotel);

        adapter = new Adapter(getActivity(), dataHotelsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        searchBar = view.findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);
        searchBar.addTextChangeListener(this);

        fetchData();

        recyclerView.addOnItemTouchListener(
                new ItemClickList(getContext(), new ItemClickList.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), DetailScrollingActivity.class);
                        intent.putExtra("nama", dataHotelsList.get(position).getName());
                        intent.putExtra("image", dataHotelsList.get(position).getImages().get(0));
                        intent.putExtra("alamat", dataHotelsList.get(position).getAddress());
                        intent.putExtra("lat", dataHotelsList.get(position).getLatitude());
                        intent.putExtra("lng", dataHotelsList.get(position).getLongitude());

                        for (int i = 0; i < dataHotelsList.get(position).getFacilities().size(); i++) {
                            fasilitas.add(dataHotelsList.get(position).getFacilities().get(i));
                        }
                        intent.putExtra("fasilitas", (ArrayList<String>) fasilitas);

                        for (int i = 0; i < dataHotelsList.get(position).getArounds().size(); i++) {
                            sekitar.add(dataHotelsList.get(position).getArounds().get(i));
                        }
                        intent.putExtra("sekitar", (ArrayList<String>) sekitar);

                        intent.putExtra("harga", dataHotelsList.get(position).getCriterias().get(0).getPivot().getValue());
                        intent.putExtra("rating", dataHotelsList.get(position).getCriterias().get(1).getPivot().getValue());

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
        );

        return view;
    }

    private void fetchData() {
        api = RestManager.getClient().create(HotelService.class);
        Call<Hotels> call = api.getAllHotel();
        call.enqueue(new Callback<Hotels>() {
            @Override
            public void onResponse(Call<Hotels> call, Response<Hotels> response) {
                Log.i("TAG", "onResponse: " + response.body().getData());
                dataHotelsList = response.body().getData();
                adapter = new Adapter(getActivity(), dataHotelsList);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Hotels> call, Throwable t) {
                Log.e("TAG", "onResponse: " + t.getMessage());
            }
        });
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
/*        if (enabled == true) {
            swipeRefreshLayout.setEnabled(false);
            swipeRefreshLayout.setRefreshing(false);
            tvError.setText("");
        } else {
            swipeRefreshLayout.setEnabled(true);
            swipeRefreshLayout.setRefreshing(true);
            tvError.setText("");
        }*/
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        searchData(searchBar.getText());

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchBar.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_BACK:
                searchBar.disableSearch();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        searchData(String.valueOf(charSequence));
    }

    @Override
    public void afterTextChanged(Editable editable) {
        searchData(String.valueOf(editable));
    }

    private void searchData(String data){
        final List<DataHotels> filterResultList = filter(dataHotelsList, data);
        adapter.animateTo(filterResultList);
        recyclerView.scrollToPosition(0);
    }

    private List<DataHotels> filter(List<DataHotels> models, String query) {

        query = query.toLowerCase();

        if (query.equals("")) {
            fetchData();
//            tvError.setText("");
        }

        final List<DataHotels> filteredModelList = new ArrayList<>();

        for (DataHotels model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
//                tvError.setText("");
            } else {
                String pesan = "Judul aduan <i>" + query + "</i>, tidak ditemukan";
//                tvError.setText(Html.fromHtml(pesan));
            }
        }

        return filteredModelList;
    }

    public interface OnFragmentInteractionListener {
    }
}
