package id.gifood.carihotel.network;

import id.gifood.carihotel.model.Hotels;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HotelService {
    @GET("hotels")
    Call<Hotels> getAllHotel();
}
