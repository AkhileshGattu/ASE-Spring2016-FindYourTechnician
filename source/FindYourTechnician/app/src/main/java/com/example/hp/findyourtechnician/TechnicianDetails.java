package com.example.hp.findyourtechnician;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class TechnicianDetails extends AppCompatActivity {

    TextView Name, Category, Experience, Fare, Contact, Email;
    String Uname, TechnicianName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_details);

        Intent intent = getIntent();
        TechnicianName = intent.getStringExtra("Name");

        Category = (TextView) findViewById(R.id.ContentTechnicianDetails_TechnicianCategory);
        Contact = (TextView) findViewById(R.id.ContentTechnicianDetails_TechnicianPhoneNumber);
        Email = (TextView) findViewById(R.id.ContentTechnicianDetails_TechnicianEmail);
        Experience = (TextView) findViewById(R.id.ContentTechnicianDetails_TechnicianExperience);
        Fare = (TextView) findViewById(R.id.ContentTechnicianDetails_TechnicianCost);
        Name = (TextView) findViewById(R.id.ContentTechnicianDetails_TechnicianName);

        Name.setText(TechnicianName);

        Firebase.setAndroidContext(this);
        Firebase TechnicianRef = new Firebase("https://findyourtechnician.firebaseio.com/Technicians");
        TechnicianRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot NameList : dataSnapshot.getChildren()) {
                    String TechFirstName = NameList.child("FirstName").getValue().toString();
                    String TechLastName = NameList.child("LastName").getValue().toString();
                    String TechFullName = TechFirstName + " " + TechLastName;
                    if (TechFullName.contentEquals(TechnicianName)) {
                        String TechExperience = NameList.child("Experience").getValue().toString();
                        String TechPhone = NameList.child("Phone").getValue().toString();
                        String TechEmail = NameList.child("EmailId").getValue().toString();
                        String TechCategory = NameList.child("Expertise").getValue().toString();
                        String TechFare = NameList.child("BaseFare").getValue().toString();
                        Uname = NameList.child("UserName").getValue().toString();

                        Category.setText(TechCategory);
                        Contact.setText(TechPhone);
                        Email.setText(TechEmail);
                        Experience.setText(TechExperience);
                        Fare.setText(TechFare);

                        break;

                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void Feedback(View view){
        Intent intent = new Intent(TechnicianDetails.this, Feedback.class);
        intent.putExtra("UserName",Uname);
        intent.putExtra("Name", TechnicianName);
        startActivity(intent);
    }

    public void CallTechnician(View view) {

        String Phone = ((TextView) findViewById(R.id.ContentTechnicianDetails_TechnicianPhoneNumber)).getText().toString();

        Intent CallTech = new Intent(Intent.ACTION_CALL, Uri.parse(Phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(CallTech);

    }

}