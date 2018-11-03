package lincolnstuart.co.pokedex.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lincolnstuart.co.pokedex.util.PokemonTypeMap;
import lincolnstuart.co.pokedex.util.PokemonTypeStrategy;

public class Pokemon implements Comparable<Pokemon> {

    @SerializedName("id")
    private int id;
    @SerializedName("forms")
    private List<PokemonForm> forms;
    @SerializedName("sprites")
    private PokemonSprite sprites;
    @SerializedName("types")
    private List<PokemonTypeObject> types;
    private PokemonTypeStrategy mainTypeStrategy;
    private List<PokemonTypeStrategy> secondaryTypeStrategies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PokemonForm> getForms() {
        return forms;
    }

    public void setForms(List<PokemonForm> forms) {
        this.forms = forms;
    }

    public PokemonSprite getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprite sprites) {
        this.sprites = sprites;
    }

    public List<PokemonTypeObject> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonTypeObject> types) {
        this.types = types;
    }

    public PokemonTypeStrategy getMainTypeSrategy() {
        fillStrategies();
        return mainTypeStrategy;
    }

    public List<PokemonTypeStrategy> getSecondaryTypeStrategies() {
        fillStrategies();
        return secondaryTypeStrategies;
    }

    private void fillStrategies(){
        if(mainTypeStrategy ==  null){
            secondaryTypeStrategies = new ArrayList<>();
            for(PokemonTypeObject pto: types) {
                if (pto.getSlot() == 1) {
                    mainTypeStrategy = PokemonTypeMap.getClassStrategy(pto.getType().getName());
                    secondaryTypeStrategies.add(mainTypeStrategy);
                    continue;
                }
                secondaryTypeStrategies.add(PokemonTypeMap.getClassStrategy(pto.getType().getName()));
            }
        }
    }

    public String getShortName() {
        String pokemonName = "";
        if (!this.forms.isEmpty()) {
            pokemonName = this.forms.get(0).getName();
        }
        return pokemonName;

    }

    public String getFullName() {
        String pokemonName = "";
        if (!this.forms.isEmpty()) {
            for (PokemonForm form : forms) {
                pokemonName += form.getName() + " / ";
            }
            pokemonName = pokemonName.substring(0, pokemonName.length() - 3);
        }
        return pokemonName;

    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pokemon){
            Pokemon pokemon = (Pokemon) obj;
            return this.id == pokemon.id;
        }
        return false;
    }

    @Override
    public int compareTo(@NonNull Pokemon pokemon) {
        return this.id - pokemon.id;
    }

}
