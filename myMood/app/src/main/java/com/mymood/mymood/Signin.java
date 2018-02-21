package com.mymood.mymood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Signin extends AppCompatActivity implements  View.OnClickListener{

    // Creació dels objectes
    private TextView tv;
    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Assignem el layout (interface) que volguem a aquesta activitat
        setContentView(R.layout.activity_main);

        // Assignem cada variable de la activitat a la seva corresponent en el layout
        tv = (TextView) findViewById(R.id.tv_hola);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);


        // Funcions que fa cada variable
        tv.setText("Hola");
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn1:
                tv.setText("Numero 1");
                break;
            case R.id.btn2:
                tv.setText("Numero 2");
                break;
            case R.id.btn3:
                tv.setText("Numero 3");
                break;
        }

    }
}
