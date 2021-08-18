package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button yesBtn;
    Button noBtn;
    TextView questionsTextView;
    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
    };
    int questionIndex = 0;

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("QuestionIndex",questionIndex);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)   {//метод выз.род метод и формирует вывод картинки на экран
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
            questionIndex = savedInstanceState.getInt("QuestionIndex", 0);

        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        questionsTextView = findViewById(R.id.questionTextView);
        questionsTextView.setText(questions[questionIndex].getQuestionText());
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (questions[questionIndex].isAnswerTrue())
                    Toast.makeText(MainActivity.this, "Правильно", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Не правильно", Toast.LENGTH_SHORT).show();
                questionIndex = (questionIndex+1)%questions.length;
                questionsTextView.setText(questions[questionIndex].getQuestionText());
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!questions[questionIndex].isAnswerTrue())
                    Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Не правильно!", Toast.LENGTH_SHORT).show();
                questionIndex = (questionIndex+1)%questions.length;
                questionsTextView.setText(questions[questionIndex].getQuestionText());
            }
        });
    }

}