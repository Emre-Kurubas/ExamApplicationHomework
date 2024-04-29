package com.emre.kurubas.examappemre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        // Find buttons
        Button btnCreateExam = findViewById(R.id.btnCreateExam);
        Button btnReviewExams = findViewById(R.id.btnReviewExams);
        Button btnLogout = findViewById(R.id.btnLogout);

        // Set click listeners
        btnCreateExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Create Exam activity
                startActivity(new Intent(TeacherDashboardActivity.this, TeacherCreateExamActivity.class));
            }
        });


        btnReviewExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Review Exams activity
                startActivity(new Intent(TeacherDashboardActivity.this, TeacherReviewExamsActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logout and open Login activity
                startActivity(new Intent(TeacherDashboardActivity.this, LoginActivity.class));
                finish(); // Close the current activity
            }

        });
    }
}