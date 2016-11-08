package com.example.jkloppenburg1.petprotector;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PetDetailsActivity extends AppCompatActivity {

    private ImageView detailsImageView;
    private TextView detailsNameTextView;
    private TextView detailsDetailsTextView;
    private TextView detailsPhoneTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);


        detailsImageView = (ImageView) findViewById(R.id.detailsImageView);
        detailsNameTextView = (TextView) findViewById(R.id.detailsNameTextView);
        detailsDetailsTextView = (TextView) findViewById(R.id.detailsDetailsTextView);
        detailsPhoneTextView = (TextView) findViewById(R.id.detailsPhoneTextView);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String details = intent.getStringExtra("Details");
        int phone = intent.getIntExtra("Phone", 0);
        String imageUri = intent.getStringExtra("Image URI");

        detailsImageView.setImageURI(Uri.parse(imageUri));
        detailsNameTextView.setText(name);
        detailsDetailsTextView.setText(details);
        detailsPhoneTextView.setText(phone);
    }
}
