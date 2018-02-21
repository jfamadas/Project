package com.mymood.mymood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class Login extends AppCompatActivity implements  View.OnClickListener{


    // Creació dels objectes
    private EditText et_password, et_user;
    private Button btn_login, btn_signin;
    private TextView tv_password, tv_user, tv_validation, tv_noaccount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Assignem el layout (interface) que volguem a aquesta activitat
        setContentView(R.layout.passwords);

        // Assignem cada variable de la activitat a la seva corresponent en el layout
        et_password = (EditText) findViewById(R.id.et_password);
        et_user = (EditText) findViewById(R.id.et_user);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        tv_password = (TextView) findViewById(R.id.tv_password);
        tv_user = (TextView) findViewById(R.id.tv_user);
        tv_validation = (TextView) findViewById(R.id.tv_validation);
        tv_noaccount = (TextView) findViewById(R.id.tv_noaccount);



        // Funcions que fa cada variable
        tv_validation.setText("INSERT USERNAME AND PASSWORD");
        tv_password.setText("Password");
        tv_user.setText("User");
        tv_noaccount.setText("Don't you have an account?");
        btn_login.setOnClickListener(this);
        btn_signin.setOnClickListener(this);
    }

    // Metode pels buttons
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_login:

                HashMap hmap = ((UsersMap) this.getApplication()).getMap();
                String usr = et_user.getText().toString();
                String psw = et_password.getText().toString();

                String psw_real = (String) hmap.get(usr);

                if(psw_real == null)
                {
                    tv_validation.setText("THIS USER DOES NOT EXIST");
                }
                else if(psw_real.equals(psw))
                {
                    tv_validation.setText("LOGIN SUCCESFUL");
                }
                else
                {
                    tv_validation.setText("WRONG PASSWORD");
                }
                break;

            case R.id.btn_signin:
                startActivity(new Intent(Login.this, MapActivity.class));
        }

    }

}
