package id.gifood.carihotel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.gifood.carihotel.R;
import id.gifood.carihotel.adapter.Adapter;
import id.gifood.carihotel.model.Hotels;
import id.gifood.carihotel.model.DataHotels;
import id.gifood.carihotel.network.HotelService;
import id.gifood.carihotel.network.RestManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ibas on 21/03/2018.
 */

public class FragmentList extends Fragment{

    private OnFragmentInteractionListener listener;

    private List<DataHotels> dataHotelsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter adapter;

    public static FragmentList newInstance(){
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

        HotelService api = RestManager.getClient().create(HotelService.class);
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

            }
        });

        return view;
    }


    public interface OnFragmentInteractionListener {
    }
}
