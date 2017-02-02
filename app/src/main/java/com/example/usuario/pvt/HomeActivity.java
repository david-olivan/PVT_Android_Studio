package com.example.usuario.pvt;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class HomeActivity extends Activity {
    private final String EXTRA_USER= "user";
    private final String EXTRA_TEST= "test";
    private EditText user;
    private Button test1;
    private Button test2;
    private Button test3;
    private String current_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = (EditText) findViewById(R.id.participant_number);
        test1 = (Button) findViewById(R.id.test_1);
        test2 = (Button) findViewById(R.id.test_2);
        test3 = (Button) findViewById(R.id.test_3);

        /*Tareas al click. Deben comprobar que el campo user no esta vacío
        * y la hora que es en 2 y 3. 2 debe dar error fuera de las 23-02
        * 3 debe dar error fuera de las 07-10 */
        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_test="test_1";
                goToNextActivity1 ();
            }
        });

        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_test="test_2";
                goToNextActivity2 ();
            }
        });

        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_test="test_3";
                goToNextActivity2 ();
            }
        });
    }

    /*Lleva a la encuesta.*/
    private void goToNextActivity1() {
        Intent intent = new Intent (HomeActivity.this,Survey.class);
        intent.putExtra (EXTRA_USER,user.getText().toString());
        intent.putExtra (EXTRA_TEST, current_test);
        startActivity(intent);
    }

    /*Lleva directo al PVT.*/
    private void goToNextActivity2() {
        Intent intent = new Intent (HomeActivity.this,PVT.class);
        intent.putExtra (EXTRA_USER,user.getText().toString());
        intent.putExtra (EXTRA_TEST, current_test);
        startActivity(intent);
    }

}
