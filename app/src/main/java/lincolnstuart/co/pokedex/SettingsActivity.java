package lincolnstuart.co.pokedex;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import lincolnstuart.co.pokedex.persistence.task.ClearScore;
import lincolnstuart.co.pokedex.persistence.task.GetScore;
import lincolnstuart.co.pokedex.persistence.task.GetSettings;
import lincolnstuart.co.pokedex.persistence.task.UpdateSettings;

public class SettingsActivity extends AppCompatActivity {

    private TextView tvWrongAnswers;
    private TextView tvRightAnswers;
    private Button btClearData;
    private Switch swKeepHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupWindowAnimations();
        configureActionBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        configureComponents();
    }

    private void configureComponents() {
        tvWrongAnswers = findViewById(R.id.tv_wrong_answer);
        tvRightAnswers = findViewById(R.id.tv_right_answer);
        new GetScore(getApplicationContext(), tvWrongAnswers, tvRightAnswers).execute();
        btClearData = findViewById(R.id.bt_clear_data);
        btClearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ClearScore(getApplicationContext(), tvWrongAnswers, tvRightAnswers).execute();
            }
        });
        swKeepHistory = findViewById(R.id.sw_keep_history);
        new GetSettings(getApplicationContext(), swKeepHistory).execute();
        swKeepHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateSettings(getApplicationContext()).execute();
            }
        });
    }

    private void configureActionBar() {
        Toolbar toolbar = findViewById(R.id.lt_action_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle(R.string.settings);
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(1000);
            window.setEnterTransition(slide);
            window.setReturnTransition(null);
        }
    }

}
