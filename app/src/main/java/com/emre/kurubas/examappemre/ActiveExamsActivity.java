package com.emre.kurubas.examappemre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActiveExamsActivity extends AppCompatActivity {

    ListView examListView;
    SharedPreferences sharedPreferences;
    List<String> examNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_exams);

        examListView = findViewById(R.id.examListView);
        sharedPreferences = getSharedPreferences("ExamData", MODE_PRIVATE);

        displayExamNames();

        // Handle item click
        examListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedExamName = examNames.get(position);
                Intent intent = new Intent(ActiveExamsActivity.this, ExamActivity.class);
                intent.putExtra("examName", selectedExamName);
                startActivity(intent);
            }
        });
    }

    private void displayExamNames() {
        examNames = new ArrayList<>();
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("questions_")) {
                String examName = key.substring(10); // Remove "questions_" prefix
                examNames.add(examName);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, examNames);
        examListView.setAdapter(adapter);
    }
}
