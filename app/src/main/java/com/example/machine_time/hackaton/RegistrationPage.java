package com.example.machine_time.hackaton;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener {

    EditText loginReg, fioReg, phoneReg, campusReg, roomReg, emailReg, passwordReg, tryPasswordReg;
    Button regBtn;

    String login, fio, phone, campus, room, email, password;

    final String URL = "http://maxim-zubarev.esy.es/porsche/api_pr/";

    Gson gson = new GsonBuilder().create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    Link auth = retrofit.create(Link.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        loginReg = (EditText)findViewById(R.id.loginReg);
        fioReg = (EditText)findViewById(R.id.fioReg);
        phoneReg = (EditText)findViewById(R.id.phoneReg);
        campusReg = (EditText)findViewById(R.id.campusReg);
        roomReg = (EditText)findViewById(R.id.roomReg);
        emailReg = (EditText)findViewById(R.id.emailReg);
        passwordReg = (EditText)findViewById(R.id.passwordReg);
        tryPasswordReg = (EditText)findViewById(R.id.tryPasswordReg);
        regBtn = (Button)findViewById(R.id.regBtn);

        regBtn.setOnClickListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.regBtn){

            login = loginReg.getText().toString();
            fio = fioReg.getText().toString();
            phone = phoneReg.getText().toString();
            campus = campusReg.getText().toString();
            room = roomReg.getText().toString();
            email = emailReg.getText().toString();
            password = passwordReg.getText().toString();

            if(passwordReg.getText().toString().equalsIgnoreCase(tryPasswordReg.getText().toString())){
                UserReg userReg = new UserReg();
                userReg.setName(fio);
                userReg.setLogin(login);
                userReg.setPass(password);
                userReg.setPhone(phone);
                userReg.setBuild(campus);
                userReg.setRoom(room);
                userReg.setEmail(email);


                String jsonReq = gson.toJson(userReg);

                System.out.println("submited json" + jsonReq);

                Call<Object> call = auth.reg(userReg);

                try {

                    Response<Object> response = call.execute();

                    System.out.println(response.body());

                    StatusApi statusApi = gson.fromJson(response.body().toString(), StatusApi.class);

                    if(statusApi.getStatus() == 200)
                    {
                        Intent auth = new Intent(this, MainActivity.class);
                        startActivity(auth);
                    }else{
                        Toast.makeText(this, statusApi.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
