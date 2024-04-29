package com.emre.kurubas.examappemre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        Button backToDashboardButton = findViewById(R.id.backToDashboardButton);
        Button reviewExamButton = findViewById(R.id.reviewExamButton);

        // Get the score from the intent
        String score = getIntent().getStringExtra("score");
        scoreTextView.setText("Congratulations! Your score is: " + score);

        backToDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the student dashboard
                Intent intent = new Intent(ResultActivity.this, StudentLoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        reviewExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open an activity to review the exam questions and answers
                Intent intent = new Intent(ResultActivity.this, TeacherReviewExamsActivity.class);
                startActivity(intent);
            }
        });
    }
}