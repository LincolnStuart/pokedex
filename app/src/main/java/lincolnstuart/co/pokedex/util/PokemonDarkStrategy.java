package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonDarkStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_dark;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_dark;
    }

    @Override
    public int getMainColor() {
        return R.color.icDark;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgDark;
    }

}
