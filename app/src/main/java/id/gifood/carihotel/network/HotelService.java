package id.gifood.carihotel.network;

import java.util.List;

import id.gifood.carihotel.model.Criterias;
import id.gifood.carihotel.model.Facility;
import id.gifood.carihotel.model.Hotels;
import id.gifood.carihotel.model.Ranges;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HotelService {
    @GET("hotels")
    Call<Hotels> getAllHotel();

    @GET("criterias")
    Call<List<Criterias>> getCriterias();

    @GET("facilities")
    Call<List<Facility>> getFacilities();
}
