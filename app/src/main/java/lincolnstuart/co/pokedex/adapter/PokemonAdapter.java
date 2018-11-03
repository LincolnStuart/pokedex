package lincolnstuart.co.pokedex.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import lincolnstuart.co.pokedex.R;
import lincolnstuart.co.pokedex.model.Pokemon;
import lincolnstuart.co.pokedex.util.PokemonTypeStrategy;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>{

    private List<Pokemon> pokemons;

    public PokemonAdapter(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.row_pokemon, parent, false);
        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.tvName.setText(pokemon.getShortName());
        holder.tvNumber.setText("#"+pokemon.getId());
        Picasso.with(holder.ivPokemon.getContext())
                .load(pokemon.getSprites().getFrontDefault())
                .into(holder.ivPokemon);
        organizeView(pokemon, holder);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    private void organizeView(Pokemon pokemon, PokemonViewHolder holder){
        holder.llInfo.removeAllViews();
        Collections.sort(pokemon.getTypes());
        holder.ivPokemon.setBackground(
                holder.ivPokemon.getContext().getDrawable(pokemon.getMainTypeSrategy().getDrawableBackground()));
        for(PokemonTypeStrategy strategy: pokemon.getSecondaryTypeStrategies()){
            ImageView typeIcon = new ImageView(holder.llInfo.getContext());
            typeIcon.setImageResource(strategy.getDrawableTypeIcon());
            holder.llInfo.addView(typeIcon);
        }
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvNumber;
        ImageView ivPokemon;
        LinearLayout llInfo;

        public PokemonViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
            ivPokemon = itemView.findViewById(R.id.iv_pokemon);
            llInfo = itemView.findViewById(R.id.ll_info);
        }
    }
}
