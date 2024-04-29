package com.emre.kurubas.examappemre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class StudentLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnActiveExams = findViewById(R.id.btnActiveExams);

        btnActiveExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentLoginActivity.this, ActiveExamsActivity.class));
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logout and open Login activity
                startActivity(new Intent(StudentLoginActivity.this, LoginActivity.class));
                finish(); // Close the current activity
            }
        });
    }
}