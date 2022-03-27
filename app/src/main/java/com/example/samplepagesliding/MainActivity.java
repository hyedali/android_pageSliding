package com.example.samplepagesliding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean isPageOpen = false;

    Animation translateLeftAnimation;
    Animation translateRightAnimation;

    LinearLayout page;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        page = findViewById(R.id.page);

        translateLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
        translateLeftAnimation.setAnimationListener(animationListener);
        translateRightAnimation.setAnimationListener(animationListener);

        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPageOpen){
                    page.startAnimation(translateRightAnimation);
                }else{
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeftAnimation);
                }
            }
        });

    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                page.setVisibility(View.INVISIBLE);

                button.setText("Open");
                isPageOpen = false;
            }else{
                button.setText("Close");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}