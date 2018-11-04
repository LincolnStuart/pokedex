package co.lincolnstuart.pokedex;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import co.lincolnstuart.pokedex.persistence.task.InitDataBase;
import co.lincolnstuart.pokedex.util.TimerSplash;

public class SplashActivity extends AppCompatActivity {

    private boolean shouldFinish = false;
    private TextView tvAppName;
    private TextView tvAuthor;
    private View vwLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_splash);
        new InitDataBase(getApplicationContext()).execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        goToMain(2000);
        fadeInLogo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shouldFinish = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(shouldFinish){
            finish();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void fadeInLogo() {
        tvAppName = findViewById(R.id.tv_app_name);
        tvAppName.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in));
        tvAuthor = findViewById(R.id.tv_author);
        tvAuthor.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in));
        vwLine = findViewById(R.id.vw_line_history);
        vwLine.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in));
    }

    public void goToMain(int millis) {
        TimerSplash timerSplash = new TimerSplash(SplashActivity.this, MainActivity.class);
        timerSplash.execute(millis);
    }

}
