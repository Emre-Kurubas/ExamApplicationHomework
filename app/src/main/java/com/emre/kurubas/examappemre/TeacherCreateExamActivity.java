package com.emre.kurubas.examappemre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences;

public class TeacherCreateExamActivity extends AppCompatActivity {

    EditText questionInput, examNameInput;
    RadioGroup radioGroup;
    Button submitQuestionBtn, submitExamBtn;
    int questionNumber = 1;
    SharedPreferences sharedPreferences;
    List<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_exam);

        questionInput = findViewById(R.id.questionInput);
        radioGroup = findViewById(R.id.radioGroup);
        submitQuestionBtn = findViewById(R.id.submitQuestionBtn);
        examNameInput = findViewById(R.id.examNameInput);
        submitExamBtn = findViewById(R.id.submitExamBtn);
        sharedPreferences = getSharedPreferences("ExamData", MODE_PRIVATE);

        submitQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitQuestion();
            }
        });

        submitExamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitExam();
            }
        });
    }

    private void submitQuestion() {
        RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String answer = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";
        questions.add(new Question(questionInput.getText().toString(), answer));

        Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();
        questionInput.getText().clear();
        radioGroup.clearCheck();
        questionNumber++;

        if (questionNumber > 5) { // You might want to adjust the limit or make it flexible.
            examNameInput.setVisibility(View.VISIBLE);
            submitExamBtn.setVisibility(View.VISIBLE);
            questionInput.setEnabled(false);
            radioGroup.setEnabled(false);
            submitQuestionBtn.setEnabled(false);
        }
    }

    private void submitExam() {
        String examName = examNameInput.getText().toString().trim();
        if (!TextUtils.isEmpty(examName)) {
            Gson gson = new Gson();
            String jsonQuestions = gson.toJson(questions);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("questions_" + examName, jsonQuestions);
            editor.apply();

            Toast.makeText(this, "Exam created successfully", Toast.LENGTH_SHORT).show();
            finish(); // Optionally close the activity
        } else {
            Toast.makeText(this, "Please enter an exam name", Toast.LENGTH_SHORT).show();
        }
    }
}

class Question {
    private String questionText;
    private String answer;

    public Question(String questionText, String answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer() {
        return answer;
    }
}