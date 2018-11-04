package co.lincolnstuart.pokedex.util;

import co.lincolnstuart.pokedex.R;

public class PokemonGhostStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_ghost;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_ghost;
    }

    @Override
    public int getMainColor() {
        return R.color.icGhost;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgGhost;
    }

}
