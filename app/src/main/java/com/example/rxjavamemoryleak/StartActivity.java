package com.example.rxjavamemoryleak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Ellison
 * @date 2019/12/15
 * @desc 用一句话描述这个类的作用
 */
public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
    }

    public void enterRxJavaCreate(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
