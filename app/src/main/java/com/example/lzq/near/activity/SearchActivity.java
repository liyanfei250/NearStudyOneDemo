package com.example.lzq.near.activity;

import android.os.Bundle;
import android.view.View;

import com.example.lzq.near.R;

import base.BaseActivity;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void onClick(View view) {

    }
}
