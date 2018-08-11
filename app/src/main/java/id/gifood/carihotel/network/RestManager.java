package id.gifood.carihotel.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestManager {
<<<<<<< HEAD
    public static final String BASE_URL = "http://192.168.100.228:8000/";
=======
    private static final String BASE_URL = "http://192.168.100.228:8000/";
>>>>>>> 29ae730be1c788031c00a2cd471e75bc9129eef1
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
