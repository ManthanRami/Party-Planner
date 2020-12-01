//FILENAME      : AddFriendsActivity.java
//PROJECT       : PROG3150 - Assignment 1
//PROGRAMMER    : Aaron Perry, Daniel Grew, John Stanley, Manthan Rami, Sasha Malesevic
//FIRST VERSION : 2020-02-08

package com.example.aperry_dgrew_mrami_jstanley_smalesevic_a1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

//CLASS   : AddFriendsActivity
//PURPOSE : To initialize the second activity of the app. Collects recipient names and emails
//          and adds them to a RecyclerView with an random sample data avatar.
public class AddFriendsActivity extends AppCompatActivity {
    public static final String EXTRA_EMAIL_LIST = "com.example.myapplicationtest.EMAIL_LIST";
    public static final String EXTRA_PARTY_NAME = "com.example.myapplicationtest.PARTY_NAME";

    private ArrayList<InviteData> inviteDataList = new ArrayList<InviteData>();
    private RecyclerView recyclerView;
    private InviteDataAdapter inviteDataAdapter;
    int[] avatarArray = new int[16];

    //METHOD      : onCreate
    //PARAM       : Bundle savedInstanceState : the bundle sent form the previous activity intent
    //DESCRIPTION : Called on activity creation. will read the intent from the previous activity
    //              and fill the appropriate TextViews. Also, it will select a random sample data
    //              avatar and initialize the recyclerFriendView.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        //get the Intent that started this activity(main_activity) and extract the string
        Intent intent = getIntent();
        String partyName = intent.getStringExtra(MainActivity.EXTRA_PARTY_NAME);

        //capture this layout's TextView for party name and set the received
        // intent's string as its text
        TextView textView = findViewById(R.id.showPartyName);
        textView.setText(partyName);

        //initialize an array with all sample avatars for displaying randomly in recyclerView
        avatarArray[0] = R.drawable.avatar_1;
        avatarArray[1] = R.drawable.avatar_2;
        avatarArray[2] = R.drawable.avatar_3;
        avatarArray[3] = R.drawable.avatar_4;
        avatarArray[4] = R.drawable.avatar_5;
        avatarArray[5] = R.drawable.avatar_6;
        avatarArray[6] = R.drawable.avatar_7;
        avatarArray[7] = R.drawable.avatar_8;
        avatarArray[8] = R.drawable.avatar_9;
        avatarArray[9] = R.drawable.avatar_10;
        avatarArray[10] = R.drawable.avatar_11;
        avatarArray[11] = R.drawable.avatar_12;
        avatarArray[12] = R.drawable.avatar_13;
        avatarArray[13] = R.drawable.avatar_14;
        avatarArray[14] = R.drawable.avatar_15;
        avatarArray[15] = R.drawable.avatar_16;

        //initialize RecyclerView and set up InviteData Adapter

        //Android Working with Recycler View
        //By Ravi Tamada
        //Retrieved from https://www.androidhive.info/2016/01/android-working-with-recycler-view/ (step 9)
        //Adapted for the purposes required in this program -> InviteData RecyclerView
        recyclerView = findViewById(R.id.recyclerFriendView);
        inviteDataAdapter = new InviteDataAdapter(inviteDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(inviteDataAdapter);
    }

    //METHOD      : addFriend
    //PARAM       : view : the current view
    //DESCRIPTION : Called when the user taps the add friend button. Input validation is done to
    //              make sure neither EditText is empty and that the email matched the emailRegex,.
    //              else a Toast will appear. After this the method will reset the EditText widgets
    //              and an AllDone button will appear on the screen to advance to the next
    //              activity with the user wishes.
    public void addFriend(View view) {
        Context context = getApplicationContext();
        EditText etName = findViewById(R.id.textFriendName);
        String name = etName.getText().toString();
        EditText etEmail = findViewById(R.id.textFriendEmail);
        String email = etEmail.getText().toString();

        //check if editText views are empty
        //display toast if empty or bad regex
        String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        //validate email
            if(email.isEmpty() || name.isEmpty()) {
                Toast.makeText(getApplicationContext(),"Enter contact info",Toast.LENGTH_SHORT).show();
            }
            else {
                //check for empty EditTexts
                if (email.trim().matches(emailRegex)) {
                    Button button = findViewById(R.id.buttonFinish);
                    button.setVisibility(View.VISIBLE);

                    //get random avatar img
                    int picture = avatarArray[getRandomNumber(1, 16)];
                    //add to recycler list
                    addInviteToRecycler(name, email, picture);
                }
                else {
                    //email does not match regex
                    Toast.makeText(getApplicationContext(),"Invalid email", Toast.LENGTH_SHORT).show();
                }
            }
        //reset focus and editText boxes
        etName.setText("");
        etEmail.setText("");
        etName.requestFocus();
    }

    //METHOD      : addInviteToRecycler
    //PARAM       : String name  : the name of the person invited to the party
    //              String email : the email of the person added to the party
    //              int picture  : the vector information for a random avatar for that person
    //DESCRIPTION : Called when the user taps the AddFriend button. This will add to the InviteData
    //              ArrayList as well as update the data adapter for the RecyclerView so it knows to
    //              update update the UI.
    private void addInviteToRecycler(String name, String email, int picture) {
        //create new obj
        InviteData inviteData = new InviteData(name, email, picture);
        //add to list
        inviteDataList.add(inviteData);
        //update adapter
        inviteDataAdapter.notifyDataSetChanged();
    }

    //METHOD      : finishParty
    //PARAM       : view : the current view
    //DESCRIPTION : Called when the user taps the AllDone button. When the user is satisfied with
    //              the list of friends invited to the party this button will advance to the next
    //              activity. It will extract the emails from the InviteData ArrayList and create a
    //              new email list to be used in the next activity, sent via intent.
    public void finishParty(View view) {
        //grab values from UI and initialize local variables
        TextView textView = findViewById(R.id.showPartyName);
        String partyName = textView.getText().toString();
        int numInvites = inviteDataList.size();
        String[] emailList = new String[numInvites];

        //pull emails from ArrayList and create email only list
        for (int i = 0; i < numInvites; i++){
            emailList[i] = inviteDataList.get(i).getEmail();
        }

        //send to new activity
        Intent intent = new Intent(AddFriendsActivity.this, FinalizeInvite.class);
        intent.putExtra(EXTRA_EMAIL_LIST, emailList);
        intent.putExtra(EXTRA_PARTY_NAME, partyName);

        startActivity(intent);
    }

    //METHOD      : getRandomNumber
    //PARAM       : int min : the min value allowed
    //              int max : the max value allowed
    //DESCRIPTION : Get a random value between the min and max values. Used in selecting a random avatar for
    //              the invite recyclerView.
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        int num = random.nextInt(max) + min;
        return num;
    }
}