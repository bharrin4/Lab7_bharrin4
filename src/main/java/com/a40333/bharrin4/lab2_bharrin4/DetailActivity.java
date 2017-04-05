package com.a40333.bharrin4.lab2_bharrin4;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by User on 2/15/2017.
 */

public class DetailActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    File photoFile = null;
    String mCurrentPhotoPath;
    String photoName;

    @Override

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ND Athletics");

        Team teamInfo = (Team) getIntent().getSerializableExtra("team");
        Game gameInfo = (Game) getIntent().getSerializableExtra("game");

        TextView gameTime = (TextView) findViewById(R.id.gameTime);
        gameTime.setText(gameInfo.getGameTime());

        TextView gameLocation = (TextView) findViewById(R.id.gameLocation);
        gameLocation.setText(gameInfo.getGameLocation());

        ImageView opposingLogo = (ImageView) findViewById(R.id.opposingLogo);
        String mDrawableName = teamInfo.getOpposingLogo();
        Log.d("logo", mDrawableName);
        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        opposingLogo.setImageResource(resID);

        TextView opposingName = (TextView) findViewById(R.id.opposingName);
        opposingName.setText(teamInfo.getOpposingName());

        TextView opposingMascot = (TextView) findViewById(R.id.opposingMascot);
        opposingMascot.setText(teamInfo.getOpposingMascot());

        TextView opposingRec = (TextView) findViewById(R.id.opposingRec);
        opposingRec.setText(teamInfo.getOpposingRec());

        TextView scoreID = (TextView) findViewById(R.id.scoreID);
        scoreID.setText(gameInfo.getScoreID());

        TextView finalString = (TextView) findViewById(R.id.finalString);
        finalString.setText(gameInfo.getFinalString());

        TextView ndName = (TextView) findViewById(R.id.ndName);
        ndName.setText(teamInfo.getNdName());

        TextView ndMascot = (TextView) findViewById(R.id.ndMascot);
        ndMascot.setText(teamInfo.getNdMascot());

        TextView ndRec = (TextView) findViewById(R.id.ndRec);
        ndRec.setText(teamInfo.getNdRec());

        ImageView ndLogo = (ImageView) findViewById(R.id.ndLogo);
        String mDrawableName2 = teamInfo.getNdLogo();
        int resID2 = getResources().getIdentifier(mDrawableName2, "drawable", getPackageName());
        ndLogo.setImageResource(resID2);

        final Button cameraButton = (Button) findViewById(R.id.cameraButton);
        cameraButton.setText("Camera");

        cameraButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        photoName = imageFileName;

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                        "com.a40333.bharrin4.lab2_bharrin4.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                Intent photoGalleryIntent = new Intent(Intent.ACTION_PICK);
                Uri imageUri = FileProvider.getUriForFile(getApplicationContext(), "com.a40333.bharrin4.lab2_bharrin4.fileprovider", photoFile);
                photoGalleryIntent.setData(imageUri);

                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    ImageView imgView = (ImageView) findViewById(R.id.photo_taken);
                    imgView.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {
            //code for sharing the schedule
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("plain/text");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BasketBall Matches");
            //shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, gameSchedule());
            startActivity(android.content.Intent.createChooser(shareIntent, "Share via"));
        }
        return true;
    }
};

