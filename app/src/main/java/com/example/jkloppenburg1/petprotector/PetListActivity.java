package com.example.jkloppenburg1.petprotector;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PetListActivity extends AppCompatActivity {

    private ListView petListView;

    private ImageView petImageView;
    private static final int REQUEST_CODE = 100;

    // This member variable stores the URI to whatever image has been selected
    // Default: none.png (R.drawable.none)
    private Uri imageURI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        petImageView = (ImageView) findViewById(R.id.petImageView);

        //
        imageURI = getUriToResource(this, R.drawable.none);

        petImageView.setImageURI(imageURI);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Code to handle when the user clones the image gallery (by selecting an image
        // or pressing the back button)

        // The Intent data is the URI selected from image
        // Decide if the user selected an image:
        if (data != null && requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            imageURI = data.getData();
            petImageView.setImageURI(imageURI);
        }
    }

    public void selectPetImage(View view)
    {
        // List of all permissions
        ArrayList<String> permList = new ArrayList<>();

        // Check if we have permission to use camera
        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED)
        {
            permList.add(Manifest.permission.CAMERA);
        }

        int readExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
        {
            permList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
        {
            permList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        // If the list has items (size > 0) request permissions from the user.
        //int requestCode = 100;
        if (permList.size() > 0)
        {
            // Convert the array list into an array of strings
            String[] perms = new String[permList.size()];


            ActivityCompat.requestPermissions(this, permList.toArray(perms), REQUEST_CODE);
        }

        if (cameraPermission == PackageManager.PERMISSION_GRANTED
                && readExternalStoragePermission == PackageManager.PERMISSION_GRANTED
                && writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED)
        {

            // Use an intent to launch gallery and take pictures

            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, REQUEST_CODE);

        }
        else
        {
            Toast.makeText(this, "Pet Protector requires camera and external storage permission",
                    Toast.LENGTH_LONG).show();
        }
    }



    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException
    {
        Resources res = context.getResources();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId));
    }
}
