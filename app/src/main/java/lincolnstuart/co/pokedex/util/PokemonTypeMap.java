package lincolnstuart.co.pokedex.util;

import java.util.HashMap;
import java.util.Map;

public abstract class PokemonTypeMap {

    private static Map<String, Class> classes;

    static {
        classes = new HashMap<>();
        classes.put(Constants.TYPE_NORMAL, PokemonNormalStrategy.class);
        classes.put(Constants.TYPE_FIGHTING, PokemonFightingStrategy.class);
        classes.put(Constants.TYPE_FLYING, PokemonFlyingStrategy.class);
        classes.put(Constants.TYPE_POISON, PokemonPoisonStrategy.class);
        classes.put(Constants.TYPE_GROUND, PokemonGroundStrategy.class);
        classes.put(Constants.TYPE_ROCK, PokemonRockStrategy.class);
        classes.put(Constants.TYPE_BUG, PokemonBugStrategy.class);
        classes.put(Constants.TYPE_GHOST, PokemonGhostStrategy.class);
        classes.put(Constants.TYPE_STEEL, PokemonSteelStrategy.class);
        classes.put(Constants.TYPE_FIRE, PokemonFireStrategy.class);
        classes.put(Constants.TYPE_WATER, PokemonWaterStrategy.class);
        classes.put(Constants.TYPE_GRASS, PokemonGrassStrategy.class);
        classes.put(Constants.TYPE_ELECTRIC, PokemonElectricStrategy.class);
        classes.put(Constants.TYPE_PSYCHIC, PokemonPsychicStrategy.class);
        classes.put(Constants.TYPE_ICE, PokemonIceStrategy.class);
        classes.put(Constants.TYPE_DRAGON, PokemonDragonStrategy.class);
        classes.put(Constants.TYPE_DARK, PokemonDarkStrategy.class);
        classes.put(Constants.TYPE_FAIRY, PokemonFairyStrategy.class);
        classes.put(Constants.TYPE_UNKNOWN, PokemonUnknownStrategy.class);
        classes.put(Constants.TYPE_SHADOW, PokemonShadowStrategy.class);
    }

    public static PokemonTypeStrategy getClassStrategy(String type){
        PokemonTypeStrategy strategy = null;
        try {
            strategy =  (PokemonTypeStrategy) classes.get(type).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            strategy = new PokemonDefaultStrategy();
        } finally {
            return strategy;
        }
    }

}
