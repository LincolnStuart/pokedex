package lincolnstuart.co.pokedex.persistence.task;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import lincolnstuart.co.pokedex.model.Score;
import lincolnstuart.co.pokedex.persistence.PokedexDatabase;
import lincolnstuart.co.pokedex.util.Constants;

public class ClearScore extends AsyncTask<Void, Void, Void>{

    private PokedexDatabase pokedexDatabase;
    private TextView tvWrongAnswers;
    private TextView tvRightAnswers;

    public ClearScore(Context applicationContext, TextView tvWrongAnswers, TextView tvRightAnswers) {
        this.tvWrongAnswers = tvWrongAnswers;
        this.tvRightAnswers = tvRightAnswers;
        pokedexDatabase = Room.databaseBuilder(applicationContext,
                PokedexDatabase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    protected Void doInBackground(Void... params) {
        pokedexDatabase.scoreAccess().updateScore(new Score());
        return null;
    }


    @Override
    protected void onPostExecute(Void param) {
        tvWrongAnswers.setText("0");
        tvRightAnswers.setText("0");
    }
}
