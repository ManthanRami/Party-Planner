//FILENAME      : FinalizeInvite.java
//PROJECT       : PROG3150 - Assignment 1
//PROGRAMMER    : Aaron Perry, Daniel Grew, John Stanley, Manthan Rami, Sasha Malesevic
//FIRST VERSION : 2020-02-08

package com.example.aperry_dgrew_mrami_jstanley_smalesevic_a1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.aperry_dgrew_mrami_jstanley_smalesevic_a1.AddFriendsActivity.EXTRA_EMAIL_LIST;
import static com.example.aperry_dgrew_mrami_jstanley_smalesevic_a1.AddFriendsActivity.EXTRA_PARTY_NAME;

//CLASS   : FinalizeInvite
//PURPOSE : To initialize the third activity of the app. Allows the user to write a personalized
//          email message and send it to their chosen recipients.
public class FinalizeInvite extends AppCompatActivity {

    private String[] emailList;
    String fullEmailList = new String();
    private String partyName;

    //METHOD      : onCreate
    //PARAM       : Bundle savedInstanceState : the bundle sent form the previous activity intent
    //DESCRIPTION : Called on activity creation. will read the intent from the previous activity
    //              and fill the appropriate TextViews.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_invite);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        Intent intent = getIntent();
        partyName = intent.getStringExtra(EXTRA_PARTY_NAME);
        emailList = intent.getStringArrayExtra(EXTRA_EMAIL_LIST);

        //create one long comma delimited string for emailing the list
        fullEmailList = String.join(", ", emailList);

        TextView textView = findViewById(R.id.textEmailList);
        textView.setText(fullEmailList);
    }

    //METHOD      : sendInvite
    //PARAM       : View view : the current view
    //DESCRIPTION : Called on when the Send button is pressed. With will create an intent that will
    //              give the user the option to send the generated party invite via email through
    //              an app of their choice.
    public void sendInvite (View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        //get recipients
        TextView etRecipients = findViewById(R.id.textEmailList);
        String recipients = etRecipients.getText().toString();
        //get message
        EditText etMessage = findViewById(R.id.textComposeMessage);
        String message = etMessage.getText().toString();


        //Android Send Email with Examples
        //By TutLane
        //Received from https://www.tutlane.com/tutorial/android/android-send-email-with-examples

        //generate email intent
        intent.putExtra(Intent.EXTRA_EMAIL,  new String[]{recipients});
        intent.putExtra(Intent.EXTRA_SUBJECT, partyName);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose Email App"));
    }
}
