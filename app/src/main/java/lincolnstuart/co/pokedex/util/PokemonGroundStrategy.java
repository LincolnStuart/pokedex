package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonGroundStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_ground;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_ground;
    }

    @Override
    public int getMainColor() {
        return R.color.icGround;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgGround;
    }

}
