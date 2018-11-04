package co.lincolnstuart.pokedex.util;

import co.lincolnstuart.pokedex.R;

public class PokemonIceStrategy implements PokemonTypeStrategy {


    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_ice;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_ice;
    }

    @Override
    public int getMainColor() {
        return R.color.icIce;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgIce;
    }

}
