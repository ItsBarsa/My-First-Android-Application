package com.barsha.first_android_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class FragmentExampleActivity extends AppCompatActivity {

    TopFragment topFragment;
    BottomFragment bottomFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);
        topFragment = new TopFragment();
        bottomFragment = new BottomFragment();


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.topContainer, topFragment);
        transaction.replace(R.id.bottomContainer, bottomFragment);
        transaction.commit();
    }
public void setBottomFragmentTitle(String value){
        bottomFragment.setTitle(value);
}

}