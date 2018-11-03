package lincolnstuart.co.pokedex.persistence.task;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import lincolnstuart.co.pokedex.model.Settings;
import lincolnstuart.co.pokedex.persistence.PokedexDatabase;
import lincolnstuart.co.pokedex.util.Constants;

public class UpdateSettings extends AsyncTask<Void, Void, Void> {

    private PokedexDatabase pokedexDatabase;

    public UpdateSettings(Context applicationContext) {
        pokedexDatabase = Room.databaseBuilder(applicationContext,
                PokedexDatabase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    protected Void doInBackground(Void... params) {
        Settings settings = pokedexDatabase.settingsAccess().getSettings();
        settings.setKeepHistory(!settings.isKeepHistory());
        pokedexDatabase.settingsAccess().updateSettings(settings);
        return null;
    }

}
