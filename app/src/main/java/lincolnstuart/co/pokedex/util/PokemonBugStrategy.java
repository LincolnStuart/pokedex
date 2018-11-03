package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonBugStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_bug;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_bug;
    }

    @Override
    public int getMainColor() {
        return R.color.icBug;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgBug;
    }

}
