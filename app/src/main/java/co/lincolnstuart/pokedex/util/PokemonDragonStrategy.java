package co.lincolnstuart.pokedex.util;

import co.lincolnstuart.pokedex.R;

public class PokemonDragonStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_dragon;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_dragon;
    }

    @Override
    public int getMainColor() {
        return R.color.icDragon;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgDragon;
    }

}
