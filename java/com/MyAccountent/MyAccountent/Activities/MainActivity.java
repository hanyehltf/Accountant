package com.MyAccountent.MyAccountent.Activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
;
import com.MyAccountent.MyAccountent.Fragment.AccountFragment;
import com.MyAccountent.MyAccountent.Fragment.BusinessFragment;
import com.MyAccountent.MyAccountent.Fragment.ChartFragment;
import com.MyAccountent.MyAccountent.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private ImageButton Chart;
    private ImageButton Money;
    private AnimationSet animationSet = new AnimationSet(true);
    private ImageButton Business;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private BusinessFragment businessFragment;
    private static ChartFragment chartFragment;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator_layout);
        navigationSetUp();
      //  toolBarSetUp();
        FragmentsState();
        loadFragment(chartFragment);
        chartVisiblaty();


    }

    private void FragmentsState() {
        businessFragment = new BusinessFragment(MainActivity.this);
        chartFragment = new ChartFragment();
        accountFragment = new AccountFragment();
    }

  /*  private void toolBarSetUp() {
        toolbar = findViewById(R.id.main_toolbar);
        drawerLayout = findViewById(R.id.drawer);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawer = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(drawer);
        drawer.syncState();

    }*/

    private void navigationSetUp() {
        Chart = (ImageButton) findViewById(R.id.chart_vector);
        Business = (ImageButton) findViewById(R.id.business_vector);
        Money = (ImageButton) findViewById(R.id.price_vector);
        bottomNavigationView = findViewById(R.id.bottom_Navigation);
        navigationView = findViewById(R.id.navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.chart:

                        chartVisiblaty();
                        loadFragment(chartFragment);

                        return true;

                    case R.id.business:


                        businessVisiblity();
                        loadFragment(businessFragment);

                        return true;
                    case R.id.price:


                        priceVisiblity();
                        loadFragment(accountFragment);

                        return true;

            }


                return false;
            }
        });
    }


    private void priceVisiblity() {


        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(200);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f);
        translateAnimation.setDuration(200);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        Money.setAnimation(animationSet);
        Money.setVisibility(View.VISIBLE);

        Chart.setVisibility(View.INVISIBLE);

        Business.setVisibility(View.INVISIBLE);
    }

    private void businessVisiblity() {

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(200);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f);
        translateAnimation.setDuration(200);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        Business.setAnimation(animationSet);
        Business.setVisibility(View.VISIBLE);
        Chart.setVisibility(View.INVISIBLE);

        Money.setVisibility(View.INVISIBLE);
    }


    private void chartVisiblaty() {

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(200);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f);
        translateAnimation.setDuration(200);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        Chart.setAnimation(animationSet);
        Chart.setVisibility(View.VISIBLE);

        Business.setVisibility(View.INVISIBLE);
        Money.setVisibility(View.INVISIBLE);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }




}

