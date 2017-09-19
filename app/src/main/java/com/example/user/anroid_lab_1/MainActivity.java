package com.example.user.anroid_lab_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int TEST_RESULT_ID = 0;
    public static final String USER_NAME = "user_name";
    public static final String USER_SURNAME = "user_surname";
    public static final String TEST_RESULT = "test_result";

    private EditText user_name_edit_text;
    private EditText user_surname_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start_test_button = (Button) findViewById(R.id.start_test_button);

        user_name_edit_text = (EditText) findViewById(R.id.name_edit_text);
        user_surname_edit_text = (EditText) findViewById(R.id.surname_edit_text);

        start_test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testIntent = new Intent(MainActivity.this, TestActivity.class);
                startActivityForResult(testIntent, TEST_RESULT_ID);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TEST_RESULT_ID){
            if(resultCode == RESULT_OK){
                int testResult = data.getIntExtra(TestActivity.TEST_RESULT, 0);
                Intent intent = new Intent(MainActivity.this, FinishTestActivity.class);
                intent.putExtra(USER_NAME, user_name_edit_text.getText().toString());
                intent.putExtra(USER_SURNAME, user_surname_edit_text.getText().toString());
                intent.putExtra(TEST_RESULT, testResult);
                startActivity(intent);
            }
        }
    }
}
