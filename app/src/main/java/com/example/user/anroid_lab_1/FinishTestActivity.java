package com.example.user.anroid_lab_1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_test);

        TextView userName = (TextView) findViewById(R.id.user_name_text_view_finish_activity);
        userName.setText("Дорогой, " + getIntent().getStringExtra(MainActivity.USER_SURNAME) + " "
                + getIntent().getStringExtra(MainActivity.USER_NAME) + ", Ваш результат:");
        TextView userResult = (TextView) findViewById(R.id.user_result_text_view_finish_activity);
        userResult.setText(getIntent().getIntExtra(MainActivity.TEST_RESULT, 0) + "/" + TestActivity.countOfQuestions);
        if(getIntent().getIntExtra(MainActivity.TEST_RESULT, 0) < (TestActivity.countOfQuestions / 2)){
            userResult.setTextColor(0xFFFF0000);
        }
    }
}
