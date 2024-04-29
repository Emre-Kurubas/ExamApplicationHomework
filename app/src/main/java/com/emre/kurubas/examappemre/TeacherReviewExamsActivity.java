package com.emre.kurubas.examappemre;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class TeacherReviewExamsActivity extends AppCompatActivity {

    LinearLayout questionsLayout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_review_exams);

        questionsLayout = findViewById(R.id.questionsLayout);
        sharedPreferences = getSharedPreferences("ExamData", MODE_PRIVATE);

        displayAllExams();
    }

    private void displayAllExams() {
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("questions_")) {
                String examName = key.substring(10); // Remove "questions_" prefix
                addExamToLayout(examName);
            }
        }
    }

    private void addExamToLayout(String examName) {
        View examView = getLayoutInflater().inflate(R.layout.item_exam_name, null);
        TextView examNameTextView = examView.findViewById(R.id.examNameTextView);
        examNameTextView.setText(examName);
        examView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayQuestionsForExam(examName);
            }
        });
        questionsLayout.addView(examView);
    }

    private void displayQuestionsForExam(String examName) {
        questionsLayout.removeAllViews();

        String json = sharedPreferences.getString("questions_" + examName, null);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {}.getType();
        List<Question> questions = gson.fromJson(json, type);

        for (Question question : questions) {
            View questionView = getLayoutInflater().inflate(R.layout.item_question, null);
            TextView questionTextView = questionView.findViewById(R.id.questionTextView);
            questionTextView.setText(question.getQuestionText());
            questionsLayout.addView(questionView);

            View answerView = getLayoutInflater().inflate(R.layout.item_answer, null);
            TextView answerTextView = answerView.findViewById(R.id.answerTextView);
            answerTextView.setText(question.getAnswer());
            questionsLayout.addView(answerView);
        }
    }
}
