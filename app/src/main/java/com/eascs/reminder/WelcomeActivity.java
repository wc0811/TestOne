package com.eascs.reminder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

public class WelcomeActivity extends AppCompatActivity implements Runnable {

    private final int DELAY_TIME = 200;

    private FloatingActionButton fab;
    private TextView nameTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        initUI();
        initData();
    }

    public void initUI() {

        fab = (FloatingActionButton) findViewById(R.id.fab);
        nameTextView = (TextView) findViewById(R.id.textView);

    }

    public void initData() {


        fab.postDelayed(this, DELAY_TIME);

    }


    @Override
    public void run() {

        final View parentView = (View) fab.getParent();
        float scale = (float) (Math.sqrt(parentView.getHeight() * parentView.getHeight() + parentView.getWidth() * parentView.getWidth()) / fab.getHeight());
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", scale);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", scale);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(fab, scaleX, scaleY).setDuration(1800);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                parentView.setBackgroundColor(ContextCompat.getColor(WelcomeActivity.this, R.color.colorPrimary));
                fab.setVisibility(View.GONE);
                nameTextView.setVisibility(View.VISIBLE);
            }
        });
        PropertyValuesHolder holderA = PropertyValuesHolder.ofFloat("alpha", 0, 1);
        PropertyValuesHolder holderYm = PropertyValuesHolder.ofFloat("translationY", 0, 300);
        ObjectAnimator textAnimator = ObjectAnimator.ofPropertyValuesHolder(R.id.textView, holderA, holderYm).setDuration(1000);
        textAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        textAnimator.setStartDelay(800);

        textAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        objectAnimator.start();
        textAnimator.start();
    }
}
