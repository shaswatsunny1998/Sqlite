package com.example.android.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etname,etmob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname=(EditText) findViewById(R.id.etname);
        etmob=(EditText) findViewById(R.id.etmob);
    }
    public void btnSubmit(View v)
    {

    }
    public void btnShowdata(View v)
    {
        startActivity(new Intent(this,Main2Activity.class));

    }
    public void btnEditData(View v)
    {

    }
    public void deleteData(View v)
    {

    }
}
