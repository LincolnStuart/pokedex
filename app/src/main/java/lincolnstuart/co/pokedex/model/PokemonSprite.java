package lincolnstuart.co.pokedex.model;

import com.google.gson.annotations.SerializedName;

public class PokemonSprite {

    @SerializedName("front_default")
    private String frontDefault;
    @SerializedName("front_female")
    private String frontFemale;
    @SerializedName("front_shiny")
    private String frontShine;
    @SerializedName("front_shiny_female")
    private String frontShineFemale;
    @SerializedName("back_default")
    private String backDefault;
    @SerializedName("back_female")
    private String backFemale;
    @SerializedName("back_shiny")
    private String backShine;
    @SerializedName("back_shiny_female")
    private String backShineFemale;

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getFrontFemale() {
        return frontFemale;
    }

    public void setFrontFemale(String frontFemale) {
        this.frontFemale = frontFemale;
    }

    public String getFrontShine() {
        return frontShine;
    }

    public void setFrontShine(String frontShine) {
        this.frontShine = frontShine;
    }

    public String getFrontShineFemale() {
        return frontShineFemale;
    }

    public void setFrontShineFemale(String frontShineFemale) {
        this.frontShineFemale = frontShineFemale;
    }

    public String getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    public String getBackFemale() {
        return backFemale;
    }

    public void setBackFemale(String backFemale) {
        this.backFemale = backFemale;
    }

    public String getBackShine() {
        return backShine;
    }

    public void setBackShine(String backShine) {
        this.backShine = backShine;
    }

    public String getBackShineFemale() {
        return backShineFemale;
    }

    public void setBackShineFemale(String backShineFemale) {
        this.backShineFemale = backShineFemale;
    }

}
