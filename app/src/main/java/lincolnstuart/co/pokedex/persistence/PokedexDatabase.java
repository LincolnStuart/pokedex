package lincolnstuart.co.pokedex.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import lincolnstuart.co.pokedex.dao.ScoreDao;
import lincolnstuart.co.pokedex.dao.SettingsDao;
import lincolnstuart.co.pokedex.model.Score;
import lincolnstuart.co.pokedex.model.Settings;

@Database(entities = {Score.class, Settings.class}, version = 1, exportSchema = false)
public abstract class PokedexDatabase extends RoomDatabase {

    public abstract ScoreDao scoreAccess();
    public abstract SettingsDao settingsAccess();

}