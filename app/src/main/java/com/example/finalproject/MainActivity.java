package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView showemail;
    Button signoutbtn, deletebtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        showemail = findViewById(R.id.showemail);
        signoutbtn = findViewById(R.id.signout);
        deletebtn = findViewById(R.id.delete);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        ;

        if (currentUser != null) {
            showemail.setText(currentUser.getEmail());
        }

        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                Intent intent = new Intent(
                        MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.delete().addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                Intent intent =
                                        new Intent(MainActivity.
                                                this, RegisterActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                );
            }
        });
    }
}