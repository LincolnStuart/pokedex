package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonSteelStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_steel;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_steel;
    }

    @Override
    public int getMainColor() {
        return R.color.icSteel;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgSteel;
    }

}
