package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    Button yesBtn;
    Button noBtn;
    TextView questionsTextView;
    Button showAnswer; // создаем переменную, кнопку
    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
    };
    int questionIndex = 0;

    /*----------------------------------------------------*/
    int trueAnswers; // переменная для хранения количества правильных ответов
    String result; //строка с вопросом и ответом на него
    String[] showResults = new String[5];; //массив строк (вопрос+ответ)
    /*----------------------------------------------------*/

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("QuestionIndex",questionIndex);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)   {//метод выз.род метод и формирует вывод  на экран
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
            questionIndex = savedInstanceState.getInt("QuestionIndex", 0);

        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        questionsTextView = findViewById(R.id.questionTextView);
        showAnswer = findViewById(R.id.showAnswer);//находим кнопку showAnswer на активности
        questionsTextView.setText(questions[questionIndex].getQuestionText());


        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                checkAnswer(true);
                if (questionIndex == 4) {
                    result = questions[questionIndex].getQuestionText() + " Ваш ответ: нет";//  записываем вопрос и ответ на него в строковую переменную
                    showResults[questionIndex] = result;//добавляем в массив очередное значение  result
                }
                result = questions[questionIndex-1].getQuestionText() + " Ваш ответ: да";//  записываем вопрос и ответ на него в строковую переменную
                showResults[questionIndex-1] = result; // добавляем в массив очередное значение  result
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                if (questionIndex == 4) {
                    result = questions[questionIndex].getQuestionText() + " Ваш ответ: нет";//  записываем вопрос и ответ на него в строковую переменную
                    showResults[questionIndex] = result;//добавляем в массив очередное значение  result
                }
                result = questions[questionIndex-1].getQuestionText() + " Ваш ответ: нет";//  записываем вопрос и ответ на него в строковую переменную
                showResults[questionIndex-1] = result; //добавляем в массив очередное значение  result

            }
        });
        showAnswer.setOnClickListener(new View.OnClickListener() { //указываем действие при нажатии кнопки
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);//создаем намерение запуска новой активности: первый параметр - какая активность запускает и вторым параметрм указываем активность, которую хотим запустить
                intent.putExtra("answer", questions[questionIndex].isAnswerTrue()); //нагрузка намерению, в интент добав.коллекцию. ключ и значение.
                startActivity(intent);//запускаем метод открытия активности
            }
        });


    }

    public void checkAnswer(boolean btn){

        if ((questions[questionIndex].isAnswerTrue() && btn) || (!questions[questionIndex].isAnswerTrue()&& !btn)) {
            trueAnswers++; //увеличили количество правильных ответов
            Toast.makeText(MainActivity.this, "Правильно", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Не правильно", Toast.LENGTH_SHORT).show();
        }
        /*--------------------------*/
        if (questionIndex == 4) {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);//создаем намерение запуска активности с результатами

            intent.putExtra("trueAnswers", trueAnswers);//передаем переменную с количеством правильных ответов
            intent.putExtra("showResults", showResults);//передаем массив с вопросами и ответами юзера

            startActivity(intent);//запускаем метод открытия активности

        }
        /*--------------------------*/

        else questionIndex = (questionIndex+1);;
        questionsTextView.setText(questions[questionIndex].getQuestionText());


    }

}

//** 1) Записываем строку "Вопрос - ваш ответ да/нет" в массив
// * 2) Складываем правильные ответы в переменню int
// * 3) Проверяем, что вопрос последний
// * 4) Если вопрос последний, то создаём интент
 //* 5) Добавляем дополнение в Интент (массив с вопросамии ответами)
 //* 6) Добавляем дополнение в Интент (int с числом правильных ответов)
 //* 7) Запускаем активность
// * 8) На запущенной активности вывести строки из массива, тем самым отобразив вопросы и ответы пользователя*//