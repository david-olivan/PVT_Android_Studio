package com.example.usuario.pvt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 14/01/2017.
 */

public class Survey extends Activity {
    private String EXTRA_USER;
    private String EXTRA_TEST;
    List<String> Survey_answers = new ArrayList<String>();

    private EditText response_1;
    private EditText response_2;
    private EditText response_3;
    private EditText response_4;
    private EditText response_5;
    private String participant_number;
    private String current_test;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.survey_layout);

        Intent intent = getIntent();
        participant_number = intent.getStringExtra(EXTRA_USER);
        current_test = intent.getStringExtra(EXTRA_TEST);

        response_1 = (EditText) findViewById(R.id.respuesta1);
        response_2 = (EditText) findViewById(R.id.respuesta2);
        response_3 = (EditText) findViewById(R.id.respuesta3);
        response_4 = (EditText) findViewById(R.id.respuesta4);
        response_5 = (EditText) findViewById(R.id.respuesta5);
        Button continue_button = (Button) findViewById(R.id.survey_button);


        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Aqui falta que compruebe que todos los campos son distintos a 0 */

                Survey_answers.add(response_1.getText().toString());
                Survey_answers.add(response_2.getText().toString());
                Survey_answers.add(response_3.getText().toString());
                Survey_answers.add(response_4.getText().toString());
                Survey_answers.add(response_5.getText().toString());
                goToPVT();
            }
        });
    }
    /* Esto hace que al clicar el boton pases de la encuesta al PVT y envia los tres datos a la actividad */
    private void goToPVT () {
        Intent intent = new Intent (Survey.this,PVT.class);
        intent.putExtra (EXTRA_USER,participant_number);
        intent.putExtra (EXTRA_TEST,current_test);
        intent.putStringArrayListExtra("Survey_answers", (ArrayList<String>) Survey_answers);
        startActivity(intent);
    }

}

