package co.lincolnstuart.pokedex;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView tvVersionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupWindowAnimations();
        configureActionBar();
        configureComponents();
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
        getSupportActionBar().setTitle(R.string.about_app);
    }

    private void configureComponents(){
        tvVersionName = findViewById(R.id.tv_version_name);
        tvVersionName.setText(BuildConfig.VERSION_NAME);
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
