package com.aplication.material.sortumanen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aplication.material.sortumanen.fragments.EventListFragment;



public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectFragment();
        initView();
    }

    public abstract void initView();

    /**
     *
     */
    public void injectFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainerFrameLayoutId, new EventListFragment())
                .commit();
    }
}
