package co.lincolnstuart.pokedex.util;

import co.lincolnstuart.pokedex.R;

public class PokemonFireStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_fire;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_fire;
    }

    @Override
    public int getMainColor() {
        return R.color.icFire;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgFire;
    }

}
