package id.gifood.carihotel.network;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import id.gifood.carihotel.model.list.Criterias;
import id.gifood.carihotel.model.list.Facility;
import id.gifood.carihotel.model.list.Hotels;
import id.gifood.carihotel.model.topsis.TopsisModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface HotelService {
    @GET("hotels")
    Call<Hotels> getAllHotel();

    @GET("criterias")
    Call<List<Criterias>> getCriterias();

    @GET("facilities")
    Call<List<Facility>> getFacilities();

    @GET("find")
    Call<JsonObject> getHotelResults(@QueryMap Map<String, String> data);

    @GET("find")
    Call<List<TopsisModel>> getHotelResultsList(@QueryMap Map<String, String> data);
}
