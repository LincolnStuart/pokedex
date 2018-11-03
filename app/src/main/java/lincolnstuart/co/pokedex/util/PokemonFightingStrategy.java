package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonFightingStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_fighting;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_fighting;
    }

    @Override
    public int getMainColor() {
        return R.color.icFighting;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgFighting;
    }

}
