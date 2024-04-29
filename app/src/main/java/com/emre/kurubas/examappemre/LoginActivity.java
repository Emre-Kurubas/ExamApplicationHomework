package com.emre.kurubas.examappemre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnTeacher = findViewById(R.id.btnTeacher);
        Button btnStudent = findViewById(R.id.btnStudent);

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Teacher LoginActivity
                Intent intent = new Intent(LoginActivity.this, TeacherDashboardActivity.class);
                startActivity(intent);
            }
        });

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Student LoginActivity
                Intent intent = new Intent(LoginActivity.this, StudentLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
