package co.lincolnstuart.pokedex.persistence.task;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import co.lincolnstuart.pokedex.model.Score;
import co.lincolnstuart.pokedex.persistence.PokedexDatabase;
import co.lincolnstuart.pokedex.util.Constants;

public class UpdateScore extends AsyncTask<Boolean, Void, Void> {

    private PokedexDatabase pokedexDatabase;

    public UpdateScore(Context applicationContext) {
        pokedexDatabase = Room.databaseBuilder(applicationContext,
                PokedexDatabase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    protected Void doInBackground(Boolean... params) {
        if(pokedexDatabase.settingsAccess().getSettings().isKeepHistory()) {
            boolean success = params[0];
            Score score = pokedexDatabase.scoreAccess().getScore();
            if (success) {
                score.setRightAnswers(score.getRightAnswers() + 1);
            } else {
                score.setWrongAnswers(score.getWrongAnswers() + 1);
            }
            pokedexDatabase.scoreAccess().updateScore(score);
        }
        return null;
    }

}
