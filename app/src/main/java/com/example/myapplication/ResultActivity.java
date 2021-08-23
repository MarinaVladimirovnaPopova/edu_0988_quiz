package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView trueAnswersTextView;
    private TextView showResultsTextView;
    private int trueAnswers;
    private String[] showResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        trueAnswers = getIntent().getIntExtra("trueAnswers", 0);//берем количество правильных ответов из MainActivity
        showResults = getIntent().getStringArrayExtra("showResults");//берем массив с вопросами и ответами юзера
        trueAnswersTextView = findViewById(R.id.trueAnswersTextView);//находим виджет на активности для отображения количества правильных ответов
        trueAnswersTextView.setText("Правильных ответов: "+Integer.toString(trueAnswers));//показываем, сколько правильных ответов на вопросы
        showResultsTextView = findViewById(R.id.showResultsTextView); //находим виджет  для отображения вопросов и ответов юзерана активности
        for (int i = 0; i < 5; i++) {
            showResultsTextView.append((i +1) + ".  " + (showResults[i]) + "\n");
        }
    }
}