package com.rafapps.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class PasswdAuthenticationActivity extends AppCompatActivity {
//    private TextView passwd_msg;
//    private Button passwd_submit;
//    private EditText passwd_in;
//    // Retrieve credentials form the server and use php files to verify pass, register pass, check whether the password exists in the db
//    private ClientAuth clientauth = new clientauth();
//    private boolean isConfirmationPending = true;
//    private String initialPasswd= "";
//
//    private void DbKey(String password) {
//        //KeyMgn keyMgn = new KeyMgn();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwd_authentication);
//
//        passwd_in = findViewById(R.id.passwd_in);
//        passwd_submit = findViewById(R.id.passwd_submit);
//        passwd_msg = findViewById(R.id.passwd_msg);
//
//// Check whether a password already exists
//        boolean valid = clientauth.checkPassword();
//
//        if (valid) {
//      // Password already exists, ask the user to enter password.
//            passwd_msg.setText("Enter your password:");
//            passwd_submit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String password = passwd_in.getText().toString();
//                    boolean passwordValid = clientauth.verifyPassword(password);
//
//                    if (passwordValid) {
//                        // Pin is valid
//                        System.out.println("Password entered by user is correct");
//
//                        // Initiate AES database Key for notes.db based on the correct password
//                        //DbKey(password);
//
//                        startActivity(new Intent(PasswdAuthenticationActivity.this, NoteActivity.class));
//                    } else {
//                        // Pin is invalid
//                        Toast.makeText(PasswdAuthenticationActivity.this, "Invalid password.", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
//            });
//        } else {
//// Pin does not exist, start registration process.
//            if (!isConfirmationPending) {
//                passwd_msg.setText("Enter a new password:");
//            } else {
//                passwd_msg.setText("Confirm password:");
//            }
//
//            passwd_submit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String password = passwd_in.getText().toString();
//
//                    if (password.isBlank()) {
//                        Toast.makeText(PasswdAuthenticationActivity.this, "Password cannot be blank.", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//                    // Check password format
//                    String regex = "^(?=.*[A-Za-z])(?=.*\\d).{8,64}$";
//                    if (!password.matches(regex)) {
//                       Toast.makeText(PasswdAuthenticationActivity.this, "Password must be at least 8 characters long, contain at least one alphabet and one number, and not exceed 64 characters.", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//                    // Confirm the password
//
//                    if (!isConfirmationPending) {
//                        isConfirmationPending = true;
//                        initialPasswd = password;
//                        passwd_in.setText("");
//                        passwd_msg.setText("Confirm password:");
//                    } else if (!initialPasswd.equals(password)) {
//                        Toast.makeText(PasswdAuthenticationActivity.this, "Passwords do not match. Please try again.", Toast.LENGTH_SHORT).show());
//                        isConfirmationPending= false;
//                        initialPasswd = "";
//                        passwd_in.setText("");
//                        passwd_msg.setText("Enter a new password:");
//                    } else {
//                        boolean passwordValid = clientauth.registerPassword(password);
//
//                        if (passwordValid) {
//                            // save the password to the server
//                            // Initiate new AES Key for notes.db and saved into SharedPreference
//                            //DbKey(password);
//
//                            startActivity(new Intent(PasswdAuthenticationActivity.this, NoteActivity.class));
//                        } else {
//                            // Error setting new password
//                           Toast.makeText(PasswdAuthenticationActivity.this, "Error setting new password, please try again.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//            });
//        }

    }
}