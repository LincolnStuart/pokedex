package lincolnstuart.co.pokedex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lincolnstuart.co.pokedex.adapter.PokemonAdapter;
import lincolnstuart.co.pokedex.model.Pokemon;
import lincolnstuart.co.pokedex.service.PokemonApi;
import lincolnstuart.co.pokedex.service.PokemonApiInterface;
import lincolnstuart.co.pokedex.util.Constants;
import lincolnstuart.co.pokedex.util.CustomOnScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonFragment extends Fragment {

    private List<Pokemon> pokemons = new ArrayList<>();
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private FloatingActionButton fbUp;
    private int currentId = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokemon, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbUp = getView().findViewById(R.id.fb_up);
        linkRecyclerView();
        addData(8);
        configureFloatingActionButton();
    }

    private void linkRecyclerView() {
        recyclerView = getView().findViewById(R.id.rv_list_pokemon);
        adapter = new PokemonAdapter(pokemons);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new CustomOnScrollListener((LinearLayoutManager) layoutManager, fbUp) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (currentId <= 10147) {
                    addData(8);
                    Snackbar.make(getView().findViewById(R.id.lt_coordinator), getString(R.string.loading_more_pokemons), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addData(int quantity) {
        PokemonApiInterface service = PokemonApi.getApi().create(PokemonApiInterface.class);
        int finalId = detectGap(quantity);
        for (; currentId <= finalId; currentId++) {
            if (containsPokemon(currentId)) {
                continue;
            }
            service.getPokemon(currentId).enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    if (response.isSuccessful()) {
                        pokemons.add(response.body());
                        Collections.sort(pokemons);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {

                }
            });
        }
    }

    private boolean containsPokemon(int idPokemon) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(idPokemon);
        return pokemons.contains(pokemon);
    }

    private void configureFloatingActionButton() {
        fbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);
            }
        });
    }

    /**
     * this method solve the pokemon's id gap beteween 802 and 10001 and put a limit on 10147
     *
     * @param quantity
     * @return finalId
     */
    private int detectGap(int quantity) {
        int finalId = currentId + quantity;
        if (finalId <= Constants.START_GAP || finalId > Constants.FINAL_GAP) {
            if (finalId > Constants.LIMIT_ID) {
                finalId = Constants.LIMIT_ID;
            }
            return finalId;
        }
        if (currentId > Constants.START_GAP) {
            currentId = Constants.FINAL_GAP;
            finalId = currentId + quantity;
            return finalId;
        }
        return Constants.START_GAP;
    }
}
