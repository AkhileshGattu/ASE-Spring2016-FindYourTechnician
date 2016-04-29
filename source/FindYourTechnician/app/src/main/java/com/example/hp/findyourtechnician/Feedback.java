package com.example.hp.findyourtechnician;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Feedback extends AppCompatActivity {

    RatingBar ratingBar;
    EditText Comment;
    String ActualRating,UserName, FullName, Feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);

        Intent intent = getIntent();
        UserName = intent.getStringExtra("UserName");
        FullName = intent.getStringExtra("Name");

        ratingBar = (RatingBar) findViewById(R.id.Feedback_rating);
        Comment = (EditText) findViewById(R.id.Feedback_Comment);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ActualRating = String.valueOf(rating);
            }
        });

    }

    public void SubmitFeedback(View view){

        final Firebase FeedbackRef = new Firebase("https://findyourtechnician.firebaseio.com/Technicians");

        Feedback = Comment.getText().toString();

        FeedbackRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FeedbackRef.child(UserName).child("Rating").setValue(ActualRating);
                FeedbackRef.child(UserName).child("Comment").setValue(Feedback);

                Intent intent = new Intent(Feedback.this, TechnicianDetails.class);
                intent.putExtra("Name", FullName);
                startActivity(intent);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
