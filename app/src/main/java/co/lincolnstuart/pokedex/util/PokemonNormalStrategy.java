package co.lincolnstuart.pokedex.util;

import co.lincolnstuart.pokedex.R;

public class PokemonNormalStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_normal;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_normal;
    }

    @Override
    public int getMainColor() {
        return R.color.icNormal;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgNormal;
    }

}
