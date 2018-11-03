package lincolnstuart.co.pokedex.persistence.task;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import lincolnstuart.co.pokedex.model.Score;
import lincolnstuart.co.pokedex.model.Settings;
import lincolnstuart.co.pokedex.persistence.PokedexDatabase;
import lincolnstuart.co.pokedex.util.Constants;

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
