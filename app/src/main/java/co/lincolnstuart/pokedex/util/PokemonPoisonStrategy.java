package co.lincolnstuart.pokedex.util;

import co.lincolnstuart.pokedex.R;

public class PokemonPoisonStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_poison;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_poison;
    }

    @Override
    public int getMainColor() {
        return R.color.icPoison;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgPoison;
    }

}
