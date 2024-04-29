package com.emre.kurubas.examappemre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    TextView examNameTextView, questionTextView;
    RadioGroup answerRadioGroup;
    RadioButton trueRadioButton, falseRadioButton;
    Button nextButton;
    SharedPreferences sharedPreferences;
    List<Question> questions;
    List<String> userAnswers;
    int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        examNameTextView = findViewById(R.id.examNameTextView);
        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        trueRadioButton = findViewById(R.id.trueRadioButton);
        falseRadioButton = findViewById(R.id.falseRadioButton);
        nextButton = findViewById(R.id.nextButton);
        sharedPreferences = getSharedPreferences("ExamData", MODE_PRIVATE);

        // Get exam name from intent
        String examName = getIntent().getStringExtra("examName");
        examNameTextView.setText(examName);

        // Load questions for the selected exam
        loadQuestions(examName);

        // Initialize user answers list
        userAnswers = new ArrayList<>();

        // Display first question
        displayQuestion();

        // Set click listener for the next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if an answer is selected
                if (answerRadioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(ExamActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Store user's answer
                RadioButton selectedRadioButton = findViewById(answerRadioGroup.getCheckedRadioButtonId());
                String selectedAnswer = selectedRadioButton.getText().toString();
                userAnswers.add(selectedAnswer);

                // Move to the next question
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayQuestion();
                } else {
                    // Calculate and display score
                    calculateAndDisplayScore();
                }
            }
        });
    }

    private void loadQuestions(String examName) {
        String json = sharedPreferences.getString("questions_" + examName, null);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {}.getType();
        questions = gson.fromJson(json, type);
    }

    private void displayQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionTextView.setText(question.getQuestionText());
        answerRadioGroup.clearCheck();
    }


        private void calculateAndDisplayScore() {
            int correctAnswers = 0;
            for (int i = 0; i < questions.size(); i++) {
                String userAnswer = userAnswers.get(i);
                String correctAnswer = questions.get(i).getAnswer();
                if (userAnswer.equals(correctAnswer)) {
                    correctAnswers++;
                }
            }

            // Calculate score percentage
            int score = (int) (((double) correctAnswers / questions.size()) * 100);

            // Start the ScoreActivity and pass the score
            Intent intent = new Intent(ExamActivity.this, ScoresActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);

            // Optionally close the activity
            finish();
        }
    }