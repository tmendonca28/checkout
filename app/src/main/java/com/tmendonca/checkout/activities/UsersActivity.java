package com.tmendonca.checkout.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tmendonca.checkout.R;

public class UsersActivity extends AppCompatActivity {
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        textViewName = (TextView) findViewById(R.id.textViewDisplayUserEmail);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome " + nameFromIntent);
    }

    public void proceedToStore(View view) {
        Intent intent = new Intent(UsersActivity.this, CatalogActivity.class);
        startActivity(intent);
    }
}
