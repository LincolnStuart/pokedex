package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonDefaultStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return 0;
    }

    @Override
    public int getDrawableTypeIcon() {
        return 0;
    }

    @Override
    public int getMainColor() {
        return R.color.primary;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.primaryDark;
    }
}
