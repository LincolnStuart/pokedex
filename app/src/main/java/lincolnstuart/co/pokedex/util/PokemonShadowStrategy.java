package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonShadowStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_shadow;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_shadow;
    }

    @Override
    public int getMainColor() {
        return R.color.icShadow;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgShadow;
    }

}
