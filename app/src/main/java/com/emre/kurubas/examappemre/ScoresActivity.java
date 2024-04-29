package com.emre.kurubas.examappemre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Get the score passed from the previous activity
        int score = getIntent().getIntExtra("score", 0);

        // Find the TextView in the layout
        TextView scoreTextView = findViewById(R.id.scoreTextView);

        // Set the score text
        scoreTextView.setText("Your Score: " + score + "%");

        // Find the button in the layout
        Button backToLoginButton = findViewById(R.id.backToLoginButton);

        // Set click listener for the button
        backToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to StudentLoginActivity
                Intent intent = new Intent(ScoresActivity.this, StudentLoginActivity.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });
    }
}
