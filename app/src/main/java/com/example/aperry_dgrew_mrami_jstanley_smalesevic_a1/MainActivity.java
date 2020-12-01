//FILENAME      : MainActivity.java
//PROJECT       : PROG3150 - Assignment 1
//PROGRAMMER    : Aaron Perry, Daniel Grew, John Stanley, Manthan Rami, Sasha Malesevic
//FIRST VERSION : 2020-02-08

package com.example.aperry_dgrew_mrami_jstanley_smalesevic_a1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//CLASS   : MainActivity
//PURPOSE : To initialize the application start page and sends and intent to the next
//          activity containing the name of the party input by the user.
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_PARTY_NAME = "com.example.myapplicationtest.PARTY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_left);
    }

    //METHOD      : submitPartyName
    //PARAM       : view : the current view
    //DESCRIPTION : Called when the user taps the Submit button. After ensuring the name editText
    //              widget is not empty it will start the next activity.
    public void submitPartyName(View view) {
        Context context = getApplicationContext();
        Intent intent = new Intent(this, AddFriendsActivity.class);
        EditText editText = findViewById(R.id.textPartyName);
        String partyName = editText.getText().toString();

        //check for empty else show a Toast
        if (partyName.isEmpty()){
            Toast.makeText(context, "Please enter a name for your party",
                    Toast.LENGTH_LONG).show();
            return;
        }

        //create intent and launch next activity
        intent.putExtra(EXTRA_PARTY_NAME, partyName);
        SlideAnimationHelper.slideOutToLeft(context, view);
        startActivity(intent);
    }
}


