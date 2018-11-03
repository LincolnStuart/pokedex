package lincolnstuart.co.pokedex;

import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lincolnstuart.co.pokedex.model.Pokemon;
import lincolnstuart.co.pokedex.persistence.task.UpdateScore;
import lincolnstuart.co.pokedex.service.PokemonApi;
import lincolnstuart.co.pokedex.service.PokemonApiInterface;
import lincolnstuart.co.pokedex.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizFragment extends Fragment {

    private Pokemon pokemon;
    private List<Pokemon> options = new ArrayList<>();
    private ImageView ivPokemonShadow;
    private ImageView ivPokemon;
    private ImageView ivRefresh;
    private TextView tvId;
    private TextView tvName;
    private TextView tvSuccess;
    private LottieAnimationView avReveal;
    private LottieAnimationView avShine;
    private LottieAnimationView avLoading;
    private LottieAnimationView avStars;
    private ConstraintLayout clOptionOne;
    private ConstraintLayout clOptionTwo;
    private ConstraintLayout clOptionThree;
    private ConstraintLayout clOptionFour;
    private ConstraintLayout clOptions;
    private TextView tvQuizQuestion;
    private TextView tvOptionOne;
    private TextView tvOptionTwo;
    private TextView tvOptionThree;
    private TextView tvOptionFour;
    private boolean shouldInit = true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onStart() {

        long time = System.currentTimeMillis();
        Log.i("LINCOLN", "ONSTART INITIAL: " +  0);

        super.onStart();
        configureComponents();
        if (shouldInit) {
            addData();
            shouldInit = false;
        }
        Log.i("LINCOLN", "ONSTART FINAL: " +  ((System.currentTimeMillis() - time)/1000));

    }

    private void configureComponents() {
        long time = System.currentTimeMillis();
        Log.i("LINCOLN", "COMPONENTS INITIAL: " +  0);

        ivPokemonShadow = getView().findViewById(R.id.iv_pokemon_shadow);
        ivPokemon = getView().findViewById(R.id.iv_pokemon);
        ivRefresh = getView().findViewById(R.id.iv_refresh);
        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshView();
            }
        });
        avReveal = getView().findViewById(R.id.av_reveal);
        avShine = getView().findViewById(R.id.av_shine);
        avLoading = getView().findViewById(R.id.av_loading);
        avLoading.setSpeed(2.5f);
        avStars = getView().findViewById(R.id.av_stars);
        clOptionOne = getView().findViewById(R.id.bt_option_one);
        clOptionTwo = getView().findViewById(R.id.bt_option_two);
        clOptionThree = getView().findViewById(R.id.bt_option_three);
        clOptionFour = getView().findViewById(R.id.bt_option_four);
        clOptions = getView().findViewById(R.id.cl_options);
        tvQuizQuestion = getView().findViewById(R.id.tv_quiz_question);
        tvOptionOne = getView().findViewById(R.id.tv_option_one);
        //tvOptionOne.setSelected(true);
        tvOptionTwo = getView().findViewById(R.id.tv_option_two);
        //tvOptionTwo.setSelected(true);
        tvOptionThree = getView().findViewById(R.id.tv_option_three);
        //tvOptionThree.setSelected(true);
        tvOptionFour = getView().findViewById(R.id.tv_option_four);
        //tvOptionThree.setSelected(true);
        tvId = getView().findViewById(R.id.tv_id);
        tvName = getView().findViewById(R.id.tv_name);
        tvSuccess = getView().findViewById(R.id.tv_success);
        Log.i("LINCOLN", "COMPONENTS FINAL: " +  ((System.currentTimeMillis() - time)/1000));

    }

    private void animateCard(boolean success) {
        clOptionOne.setOnClickListener(null);
        clOptionTwo.setOnClickListener(null);
        clOptionThree.setOnClickListener(null);
        clOptionFour.setOnClickListener(null);
        avReveal.setVisibility(View.VISIBLE);
        avLoading.setVisibility(View.INVISIBLE);
        avReveal.playAnimation();
        avShine.playAnimation();
        if (success) {
            avStars.playAnimation();
            tvSuccess.setText(getString(R.string.right_answer));
        } else {
            tvSuccess.setText(getString(R.string.wrong_answer));
        }
        ivPokemon.setVisibility(View.VISIBLE);
        //ivPokemon.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
        //tvId.setVisibility(View.VISIBLE);
        tvId.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
        //tvName.setVisibility(View.VISIBLE);
        tvName.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
        tvSuccess.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
        ivRefresh.setVisibility(View.VISIBLE);
        //ivRefresh.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
        //clOptions.setVisibility(View.INVISIBLE);
        clOptions.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        tvQuizQuestion.setVisibility(View.INVISIBLE);
        ivPokemonShadow.setColorFilter(getResources().getColor(pokemon.getMainTypeSrategy().getMainColor()));
        ivPokemonShadow.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in_post));
    }

    private void refreshView() {
        avLoading.setVisibility(View.VISIBLE);
        avReveal.cancelAnimation();
        avShine.cancelAnimation();
        avStars.cancelAnimation();
        avReveal.setProgress(0);
        avShine.setProgress(0);
        avStars.setProgress(0);
        addData();
        //avReveal.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        avReveal.setVisibility(View.INVISIBLE);
        tvId.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        //tvId.setVisibility(View.INVISIBLE);
        tvName.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        tvSuccess.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        //tvName.setVisibility(View.INVISIBLE);
        //ivRefresh.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        ivRefresh.setVisibility(View.INVISIBLE);
        //ivPokemon.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        ivPokemon.setVisibility(View.INVISIBLE);
        //clOptionOne.setVisibility(View.INVISIBLE);
        //clOptionOne.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        //clOptionTwo.setVisibility(View.INVISIBLE);
        //clOptionTwo.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        //clOptionThree.setVisibility(View.INVISIBLE);
        //clOptionThree.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        //clOptionFour.setVisibility(View.INVISIBLE);
        //clOptionFour.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        //ivPokemonShadow.setVisibility(View.INVISIBLE);
        ivPokemonShadow.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out_fast));
        //clOptions.setVisibility(View.VISIBLE);
        //clOptions.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        //tvQuizQuestion.setVisibility(View.VISIBLE);
        tvQuizQuestion.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
        ivPokemonShadow.setColorFilter(null);
    }

    public void addData() {
        final long time = System.currentTimeMillis();
        Log.i("LINCOLN", "INITIAL: " + 0);
        options = new ArrayList<>();
        pokemon = null;
        List<Integer> ids = getRandomId();
        PokemonApiInterface service = PokemonApi.getApi().create(PokemonApiInterface.class);
        for (Integer id : ids) {
            service.getPokemon(id).enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Log.i("LINCOLN", "INITIALFINAL: " +  ((System.currentTimeMillis() - time)/1000));
                    if (response.isSuccessful()) {
                        Log.i("LINCOLN", "SUCCESS");
                        Pokemon currentPokemon = response.body();
                        if (pokemon == null && currentPokemon.getSprites().getFrontDefault() != null) {
                            Picasso.with(ivPokemonShadow.getContext())
                                    .load(currentPokemon.getSprites().getFrontDefault())
                                    .into(ivPokemonShadow);
                            Picasso.with(ivPokemon.getContext())
                                    .load(currentPokemon.getSprites().getFrontDefault())
                                    .into(ivPokemon);
                            tvId.setText("#" + currentPokemon.getId());
                            tvName.setText(currentPokemon.getShortName());
                            currentPokemon.getMainTypeSrategy();
                            pokemon = currentPokemon;
                            SimpleColorFilter filterReveal = new SimpleColorFilter(getResources().getColor(pokemon.getMainTypeSrategy().getSecondaryColor()));
                            SimpleColorFilter filterShine = new SimpleColorFilter(getResources().getColor(pokemon.getMainTypeSrategy().getMainColor()));
                            KeyPath keyPath = new KeyPath("**");
                            LottieValueCallback<ColorFilter> callbackReveal = new LottieValueCallback<ColorFilter>(filterReveal);
                            LottieValueCallback<ColorFilter> callbackShine = new LottieValueCallback<ColorFilter>(filterShine);
                            avReveal.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callbackReveal);
                            avShine.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callbackShine);

                        }
                        options.add(currentPokemon);
                        if (options.size() == 4) {
                            Collections.shuffle(options);
                            //tvQuizQuestion.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
                            avLoading.setVisibility(View.INVISIBLE);
                            //avLoading.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
                            //ivPokemonShadow.setVisibility(View.VISIBLE);
                            ivPokemonShadow.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in));
                            tvOptionOne.setText(options.get(0).getShortName());
                            //tvOptionOne.setEllipsize(TextUtils.TruncateAt.END);
                            //tvOptionOne.setSingleLine();
                            //tvOptionOne.setSelected(true);
                            //clOptionOne.setVisibility(View.VISIBLE);
                            //clOptionOne.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
                            tvOptionTwo.setText(options.get(1).getShortName());
                            //tvOptionTwo.setEllipsize(TextUtils.TruncateAt.END);
                            //tvOptionTwo.setSingleLine();
                            //tvOptionTwo.setSelected(true);
                            //clOptionTwo.setVisibility(View.VISIBLE);
                            //clOptionTwo.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
                            tvOptionThree.setText(options.get(2).getShortName());
                            //tvOptionThree.setEllipsize(TextUtils.TruncateAt.END);
                            //tvOptionThree.setSingleLine();
                            //tvOptionThree.setSelected(true);
                            //clOptionThree.setVisibility(View.VISIBLE);
                            //clOptionThree.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
                            tvOptionFour.setText(options.get(3).getShortName());
                            //tvOptionFour.setEllipsize(TextUtils.TruncateAt.END);
                            //tvOptionFour.setSingleLine();
                            //tvOptionFour.setSelected(true);
                            //clOptionFour.setVisibility(View.VISIBLE);
                            //clOptionFour.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
                            clOptions.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_fast));
                            clOptionOne.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    boolean success = options.get(0).getId() == pokemon.getId();
                                    animateCard(success);
                                    new UpdateScore(getContext().getApplicationContext()).execute(success);
                                }
                            });
                            clOptionTwo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    boolean success = options.get(1).getId() == pokemon.getId();
                                    animateCard(success);
                                    new UpdateScore(getContext().getApplicationContext()).execute(success);
                                }
                            });
                            clOptionThree.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    boolean success = options.get(2).getId() == pokemon.getId();
                                    animateCard(success);
                                    new UpdateScore(getContext().getApplicationContext()).execute(success);
                                }
                            });
                            clOptionFour.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    boolean success = options.get(3).getId() == pokemon.getId();
                                    animateCard(success);
                                    new UpdateScore(getContext().getApplicationContext()).execute(success);
                                }
                            });
                        }
                    } else {
                        Log.i("LINCOLN", "INSUCCESS");
                    }
                    Log.i("LINCOLN", "FINAL: " +  ((System.currentTimeMillis() - time)/1000));
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {

                }
            });
        }
    }

    private List<Integer> getRandomId() {
        List<Integer> proportion = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        Random random = new Random();
        do {
            int id = random.nextInt(Constants.START_GAP) + 1;
            if (!proportion.contains(id)) {
                proportion.add(id);
            }
        } while (proportion.size() < 14);
        do {
            int id = random.nextInt(Constants.LIMIT_ID - Constants.FINAL_GAP) + Constants.FINAL_GAP;
            if (!proportion.contains(id)) {
                proportion.add(id);
            }
        } while (proportion.size() < 16);
        do {
            int id = random.nextInt(16);
            if (!ids.contains(proportion.get(id))) {
                ids.add(proportion.get(id));
            }
        } while (ids.size() < 4);
        return ids;
    }

}
