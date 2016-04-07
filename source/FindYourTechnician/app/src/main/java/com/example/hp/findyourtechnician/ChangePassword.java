package com.example.hp.findyourtechnician;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePassword extends AppCompatActivity {

    String Password;
    EditText ActivityChangePassword_CurrentPassword,ActivityChangePassword_NewPassword,ActivityChangePassword_ConfirmPassword;
    Button ActivityUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent intent = getIntent();
        Password = intent.getStringExtra("Password");
        ActivityChangePassword_CurrentPassword = (EditText)findViewById(R.id.ChangePassword_CurrentPassword);
        ActivityChangePassword_NewPassword = (EditText)findViewById(R.id.ChangePassword_NewPassword);
        ActivityChangePassword_ConfirmPassword = (EditText)findViewById(R.id.ChangePassword_ConfirmPassword);
        ActivityUpdateButton = (Button)findViewById(R.id.ChangePassword_UpdateButton);


    }
    public void UpdateProfile(View view){
        if(ActivityChangePassword_CurrentPassword.getText().toString().contentEquals(Password)){
            if(ActivityChangePassword_NewPassword.getText().toString().contentEquals(ActivityChangePassword_ConfirmPassword.getText().toString())){
                if((ActivityChangePassword_NewPassword.length() >= 8) && (ActivityChangePassword_NewPassword.length() <= 15)){
                    Intent intent = new Intent(ChangePassword.this,EditProfilePage.class);
                    intent.putExtra("password",Password);
                    startActivity(intent);
                }
                else{
                    ActivityChangePassword_NewPassword.setError("Password should be between 8 and 15 characters");
                    ActivityChangePassword_NewPassword.requestFocus();
                }
            }
            else{
                ActivityChangePassword_ConfirmPassword.setError("New Password and Confirm Password should be same");
                ActivityChangePassword_NewPassword.requestFocus();
            }
        }
        else{
            ActivityChangePassword_CurrentPassword.setError("Please Enter the correct password");
            ActivityChangePassword_CurrentPassword.requestFocus();
        }
    }

}