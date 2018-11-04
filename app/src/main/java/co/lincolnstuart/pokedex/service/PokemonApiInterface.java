package co.lincolnstuart.pokedex.service;

import co.lincolnstuart.pokedex.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApiInterface {

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);

}
