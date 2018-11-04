package co.lincolnstuart.pokedex.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import co.lincolnstuart.pokedex.model.Settings;

@Dao
public interface SettingsDao {

    @Insert
    void insertSettings(Settings settings);

    @Update
    void updateSettings(Settings settings);

    @Query("SELECT * FROM Settings WHERE pk = 1")
    Settings getSettings();

}
