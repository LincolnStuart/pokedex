package co.lincolnstuart.pokedex.util;

import co.lincolnstuart.pokedex.R;

public class PokemonUnknownStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_unknown;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_unknown;
    }

    @Override
    public int getMainColor() {
        return R.color.icUnknown;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgUnknown;
    }

}
