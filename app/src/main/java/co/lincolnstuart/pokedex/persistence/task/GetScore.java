package co.lincolnstuart.pokedex.persistence.task;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import co.lincolnstuart.pokedex.model.Score;
import co.lincolnstuart.pokedex.persistence.PokedexDatabase;
import co.lincolnstuart.pokedex.util.Constants;

public class GetScore extends AsyncTask<Void, Void, Score>{

    private PokedexDatabase pokedexDatabase;
    private TextView tvWrongAnswers;
    private TextView tvRightAnswers;

    public GetScore(Context applicationContext, TextView tvWrongAnswers, TextView tvRightAnswers) {
        this.tvWrongAnswers = tvWrongAnswers;
        this.tvRightAnswers = tvRightAnswers;
        pokedexDatabase = Room.databaseBuilder(applicationContext,
                PokedexDatabase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    protected Score doInBackground(Void... params) {
        return pokedexDatabase.scoreAccess().getScore();
    }

    @Override
    protected void onPostExecute(Score score) {
        if(score != null) {
            tvWrongAnswers.setText(score.getWrongAnswers()+ "");
            tvRightAnswers.setText(score.getRightAnswers()+ "");
        }
    }
}
