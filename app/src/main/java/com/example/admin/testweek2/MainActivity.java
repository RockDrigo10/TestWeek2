package com.example.admin.testweek2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements  CarFragment.OnFragmentInteractionListener,ListFragment.OnFragmentInteractionListener{
    private static final String FRAGMENT_LIST = "list";
    private static final String FRAGMENT_CAR = "car";
    private static final String TAG = "MainAcitvity";
    FrameLayout flList,flCars;
    ListFragment listFragment;
    CarFragment carFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        flList = (FrameLayout) findViewById(R.id.flList);
        flCars = (FrameLayout) findViewById(R.id.flCars);

        carFragment = new CarFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.flCars,carFragment,FRAGMENT_CAR)
                .commit();
        listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.flList,listFragment,FRAGMENT_LIST)
                .commit();


    }

    @Override
    public void onFragmentInteraction(String string) {
        ////ListFragment
        CarFragment carFragment = CarFragment.newInstance(string,"");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flCars,carFragment,FRAGMENT_CAR)
                .commit();
        Log.d(TAG, "onFragmentInteraction: " + string);
    }
    @Override
    public void onFragmentInteraction(int integer) {
        ////CarFragment
    }
}
