package lincolnstuart.co.pokedex.util;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

public class TimerSplash extends AsyncTask<Integer, Void, Void> {

    private AppCompatActivity from;
    private Class<?> to;

    public TimerSplash(AppCompatActivity from, Class<?> to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        try {
            Thread.sleep(params[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Intent intent = new Intent(from, to);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(from);
        from.startActivity(intent, options.toBundle());
    }
}
