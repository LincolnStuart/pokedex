package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonFairyStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_fairy;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_fairy;
    }

    @Override
    public int getMainColor() {
        return R.color.icFairy;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgFairy;
    }

}
