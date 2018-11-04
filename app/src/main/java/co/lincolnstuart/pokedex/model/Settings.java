package co.lincolnstuart.pokedex.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Settings {

    @PrimaryKey
    private int pk;
    private boolean keepHistory;

    public Settings(){
        this.pk = 1;
        this.keepHistory = true;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public boolean isKeepHistory() {
        return keepHistory;
    }

    public void setKeepHistory(boolean keepHistory) {
        this.keepHistory = keepHistory;
    }
}
