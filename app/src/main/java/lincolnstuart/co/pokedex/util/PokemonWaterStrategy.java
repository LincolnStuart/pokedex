package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonWaterStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_water;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_water;
    }

    @Override
    public int getMainColor() {
        return R.color.icWater;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgWater;
    }

}
