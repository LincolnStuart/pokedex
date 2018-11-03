package lincolnstuart.co.pokedex.persistence.task;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Switch;

import lincolnstuart.co.pokedex.model.Settings;
import lincolnstuart.co.pokedex.persistence.PokedexDatabase;
import lincolnstuart.co.pokedex.util.Constants;

public class GetSettings extends AsyncTask<Void, Void, Settings>{

    private PokedexDatabase pokedexDatabase;
    private Switch swKeepHistory;

    public GetSettings(Context applicationContext, Switch swKeepHistory) {
        this.swKeepHistory = swKeepHistory;
        pokedexDatabase = Room.databaseBuilder(applicationContext,
                PokedexDatabase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    protected Settings doInBackground(Void... params) {
        return pokedexDatabase.settingsAccess().getSettings();
    }

    @Override
    protected void onPostExecute(Settings settings) {
        if(swKeepHistory != null){
            swKeepHistory.setChecked(settings.isKeepHistory());
        }
    }
}
