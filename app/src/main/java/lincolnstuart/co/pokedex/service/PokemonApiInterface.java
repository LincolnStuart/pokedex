package lincolnstuart.co.pokedex.service;

import lincolnstuart.co.pokedex.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApiInterface {

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);

}
