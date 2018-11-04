package co.lincolnstuart.pokedex.util;

import co.lincolnstuart.pokedex.R;

public class PokemonGrassStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_grass;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_grass;
    }

    @Override
    public int getMainColor() {
        return R.color.icGrass;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgGrass;
    }

}
