package com.example.friendsearch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class NewProfile extends AppCompatActivity {

    public static final int CAMERA_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        // ask for Camera permission
        if (ContextCompat.checkSelfPermission(NewProfile.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                    NewProfile.this, new String[] {Manifest.permission.CAMERA},
                    CAMERA_REQUEST_CODE);
        }

        // set the photo button
        Button newProfileButton = findViewById(R.id.photo_button);
        newProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(NewProfile.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(NewProfile.this, "Camera permission not granted", Toast.LENGTH_SHORT);
                } else {
                    takePhoto();
                }
            }
        });

        // set the createProfile button
        Button createProfileButton = findViewById(R.id.create_profile_button);
        createProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ToDo: update the main activity (?)
                Intent intent = new Intent(NewProfile.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == CAMERA_REQUEST_CODE
                && resultCode == RESULT_OK) {
            Bitmap bmp = (Bitmap) intent.getExtras().get("data");
            ImageView img = (ImageView) findViewById(R.id.new_profile_picture_image_view);
            img.setImageBitmap(bmp);
            Toast.makeText(this, "Photo taken!", Toast.LENGTH_SHORT).show();
        }
    }
}
