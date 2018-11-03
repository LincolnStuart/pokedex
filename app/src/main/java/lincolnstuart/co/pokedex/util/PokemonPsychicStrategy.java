package lincolnstuart.co.pokedex.util;

import lincolnstuart.co.pokedex.R;

public class PokemonPsychicStrategy implements PokemonTypeStrategy {

    @Override
    public int getDrawableBackground() {
        return R.drawable.bg_circle_psychic;
    }

    @Override
    public int getDrawableTypeIcon() {
        return R.drawable.ic_psychic;
    }

    @Override
    public int getMainColor() {
        return R.color.icPsychic;
    }

    @Override
    public int getSecondaryColor() {
        return R.color.bgPsychic;
    }

}
