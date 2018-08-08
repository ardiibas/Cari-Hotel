package id.gifood.carihotel.network;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import id.gifood.carihotel.model.Criterias;
import id.gifood.carihotel.model.DataHotels;
import id.gifood.carihotel.model.Facility;
import id.gifood.carihotel.model.Hotels;
import id.gifood.carihotel.model.Ranges;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
    Call<List<DataHotels>> getHotelResultsList(@QueryMap Map<String, String> data);
}
