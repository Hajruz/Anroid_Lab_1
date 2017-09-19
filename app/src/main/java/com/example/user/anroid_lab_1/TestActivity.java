package com.example.user.anroid_lab_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.user.anroid_lab_1.CSVReader.CSV;

public class TestActivity extends AppCompatActivity {

    public final static String TEST_RESULT = "test_result";
    private final static int CSV_WIDTH = 5;
    private final static int CSV_HEIGHT = 1;
    private final static int COUNT_OF_ANSWERS = 3;
    public static int countOfQuestions;

    private Button next_button;
    private TextView question_text_view;
    private TextView question_num_text_view;
    private RadioGroup radioGroup;
    private RadioButton[] radio_buttons;
    private String[] questions;

    private int answer_true;
    private int current_question;
    private int current_result;
    private int current_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        questions = getResources().getStringArray(R.array.questions);
        countOfQuestions = questions.length;
        question_num_text_view = (TextView) findViewById(R.id.question_text_view);
        question_num_text_view.setText(question_num_text_view.getText().toString() + " " + (current_question + 1));

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.clearCheck();
        radio_buttons = new RadioButton[COUNT_OF_ANSWERS];
        for(int rb = 0; rb < radio_buttons.length; rb++){
            radio_buttons[rb] = new RadioButton(this);
            final int finalRb = rb;
            radioGroup.addView(radio_buttons[rb]);
            radio_buttons[rb].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current_answer = finalRb;
                }
            });
        }

        current_question = 0;
        current_result = 0;
        current_answer = -1;

        next_button = (Button) findViewById(R.id.next_question_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_answer != -1) {
                    if(current_answer == answer_true) {
                        current_result++;
                    }

                    current_answer = -1;
                    current_question++;
                    radioGroup.clearCheck();
                    if(current_question < questions.length) {
                        question_num_text_view.setText("Вопрос " + (current_question + 1));
                        generateQuestion();
                    }
                    else {
                        Intent testResult = new Intent();
                        testResult.putExtra(TEST_RESULT, current_result);
                        setResult(RESULT_OK, testResult);
                        finish();
                    }
                }
            }
        });

        question_text_view = (TextView) findViewById(R.id.question_text_text_view);
        generateQuestion();
    }

    private void generateQuestion() {
        String[][] csv_result;

        csv_result = CSV.read(questions[current_question], CSV_WIDTH, CSV_HEIGHT, '/');
        question_text_view.setText(csv_result[0][0]);
        radio_buttons[0].setText(csv_result[0][1]);
        radio_buttons[1].setText(csv_result[0][2]);
        radio_buttons[2].setText(csv_result[0][3]);
        answer_true = Integer.parseInt(csv_result[0][4]);

    }
}
