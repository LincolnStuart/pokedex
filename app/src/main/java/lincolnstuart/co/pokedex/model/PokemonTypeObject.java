package lincolnstuart.co.pokedex.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class PokemonTypeObject implements Comparable<PokemonTypeObject>{

    @SerializedName("slot")
    private int slot;
    @SerializedName("type")
    private PokemonType type;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }

    @Override
    public int compareTo(@NonNull PokemonTypeObject pokemonTypeObject) {
        return this.slot - pokemonTypeObject.slot;
    }
}
