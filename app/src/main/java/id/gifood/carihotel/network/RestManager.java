package id.gifood.carihotel.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestManager {

<<<<<<< HEAD
    public static final String BASE_URL = "http://192.168.1.8:8000/";
=======
    public static final String BASE_URL = "http://192.168.1.11:8000/";
>>>>>>> 6f655992d9606c0c1f63588972de20139dd8dd1c

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
