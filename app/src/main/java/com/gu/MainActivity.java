package com.gu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gu.cyclelayout.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
    }

    public void bgclick(View view) {
        Toast.makeText(this,"bgclick",Toast.LENGTH_LONG).show();
    }
}
