package lincolnstuart.co.pokedex.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonApi {

    public static final String BASE_URL ="https://pokeapi.co/api/v2/";
    private static Retrofit retrofit;

    public static Retrofit getApi(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
