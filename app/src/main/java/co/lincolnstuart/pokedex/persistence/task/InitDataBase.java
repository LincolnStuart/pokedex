package co.lincolnstuart.pokedex.persistence.task;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import co.lincolnstuart.pokedex.model.Score;
import co.lincolnstuart.pokedex.model.Settings;
import co.lincolnstuart.pokedex.persistence.PokedexDatabase;
import co.lincolnstuart.pokedex.util.Constants;

public class InitDataBase extends AsyncTask<Void, Void, Void> {

    private PokedexDatabase pokedexDatabase;

    public InitDataBase(Context applicationContext) {
        pokedexDatabase = Room.databaseBuilder(applicationContext,
                PokedexDatabase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (pokedexDatabase.scoreAccess().getScore() == null) {
            pokedexDatabase.scoreAccess().insertScore(new Score());
        }
        if (pokedexDatabase.settingsAccess().getSettings() == null) {
            pokedexDatabase.settingsAccess().insertSettings(new Settings());
        }
        return null;
    }

}
