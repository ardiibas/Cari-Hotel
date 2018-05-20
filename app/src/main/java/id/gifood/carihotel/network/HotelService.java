package id.gifood.carihotel.network;

import java.util.List;

import id.gifood.carihotel.model.HotelModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HotelService {
    @GET(".../hotel.php")
    Call<List<HotelModel>> getAllHotel();
}
