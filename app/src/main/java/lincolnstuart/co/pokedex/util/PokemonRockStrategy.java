package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonRockStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_rock;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_rock;
    }

    @Override
    public int getMainColor() {
        return R.color.icRock;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgRock;
    }

}
