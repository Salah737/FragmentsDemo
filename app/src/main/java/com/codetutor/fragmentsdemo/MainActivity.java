package com.codetutor.fragmentsdemo;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String COMMON_TAG = "CombinedLifeCycle";
    private static final String ACTIVITY_NAME = MainActivity.class.getSimpleName();
    private static final String TAG = ACTIVITY_NAME;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private Button buttonAddFragmentOne, buttonAddFragmentTwo, buttonAddFragmentThree;
    private TextView textViewFragmentCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddFragmentOne = (Button)findViewById(R.id.buttonAddFragmentOne);
        buttonAddFragmentTwo = (Button)findViewById(R.id.buttonAddFragmentTwo);
        buttonAddFragmentThree = (Button)findViewById(R.id.buttonAddFragmentThree);
        textViewFragmentCount = (TextView)findViewById(R.id.textViewFragmentCount);

        fragmentManager=getSupportFragmentManager();


        textViewFragmentCount.setText("Fragment count in back stack: "+fragmentManager.getBackStackEntryCount());

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                textViewFragmentCount.setText("Fragment count in back stack: "+fragmentManager.getBackStackEntryCount());
            }
        });

        Log.i(TAG,"Initial BackStackEntryCount: "+fragmentManager.getBackStackEntryCount());


        buttonAddFragmentOne.setOnClickListener(this);
        buttonAddFragmentTwo.setOnClickListener(this);
        buttonAddFragmentThree.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void addFragment(){
        Fragment fragment;
        switch (fragmentManager.getBackStackEntryCount()){
            case 0: fragment = new SampleFragment(); break;
            case 1: fragment = new FragmentTwo();break;
            case 2: fragment = new FragmentThree(); break;
            default: fragment = new SampleFragment(); break;
        }
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,fragment,"demofragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void loadFragmentOne(){
        Fragment fragment;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if(fragment!=null){
            fragmentTransaction.remove(fragment);
        }
        fragment = new SampleFragment();
        fragmentTransaction.add(R.id.fragmentContainer,fragment,"demofragment");
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



    }

    private void loadFragmentTwo(){

        Fragment fragment;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if(fragment!=null){
            fragmentTransaction.remove(fragment);
        }
        fragment = new FragmentTwo();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment,"demofragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void loadFragmentThree(){
        Fragment fragment;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if(fragment!=null){
            fragmentTransaction.remove(fragment);
        }
        fragment = new FragmentThree();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment,"demofragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAddFragmentOne: loadFragmentOne(); break;
            case R.id.buttonAddFragmentTwo: loadFragmentTwo();break;
            case R.id.buttonAddFragmentThree: loadFragmentThree();break;
            default: break;
        }
    }
}
