package co.lincolnstuart.pokedex.util;

import co.lincolnstuart.pokedex.R;

public class PokemonElectricStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_electric;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_electric;
    }

    @Override
    public int getMainColor() {
        return R.color.icElectric;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgElectric;
    }

}
