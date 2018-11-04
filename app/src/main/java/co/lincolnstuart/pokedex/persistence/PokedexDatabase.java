package co.lincolnstuart.pokedex.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import co.lincolnstuart.pokedex.dao.ScoreDao;
import co.lincolnstuart.pokedex.dao.SettingsDao;
import co.lincolnstuart.pokedex.model.Score;
import co.lincolnstuart.pokedex.model.Settings;

@Database(entities = {Score.class, Settings.class}, version = 1, exportSchema = false)
public abstract class PokedexDatabase extends RoomDatabase {

    public abstract ScoreDao scoreAccess();
    public abstract SettingsDao settingsAccess();

}