package com.example.machine_time.hackaton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.io.StringReader;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText login_auth, password_auth;
    Button authBtn, registrationBtn;

    String login, password;

    final String URL = "http://maxim-zubarev.esy.es/porsche/api_pr/";

    Gson gson = new GsonBuilder().create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    Link auth = retrofit.create(Link.class);

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        login_auth = (EditText)findViewById(R.id.login_auth);
        password_auth = (EditText)findViewById(R.id.password_auth);
        authBtn = (Button)findViewById(R.id.authBtn);
        registrationBtn = (Button)findViewById(R.id.registrationBtn);

        authBtn.setOnClickListener(this);
        registrationBtn.setOnClickListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        sPref = getSharedPreferences("savedData", 0);
        if(sPref.getString("is_auth", "").equalsIgnoreCase("true")){
            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.authBtn:

                login = login_auth.getText().toString();
                password = password_auth.getText().toString();

                if(!login.equalsIgnoreCase("") && !password.equalsIgnoreCase("")){
                    User user = new User();
                    user.setLogin(login);
                    user.setPass(password);

                    String jsonReq = gson.toJson(user);

                    System.out.println("submited json" + jsonReq);

                    Call<Object> call = auth.getSt(user);

                    try {

                        Response<Object> response = call.execute();

                        System.out.println(response.body());

                        StatusApi statusApi = gson.fromJson(response.body().toString(), StatusApi.class);


                        if(statusApi.getStatus() == 200)
                        {
                            sPref = getSharedPreferences("savedData", 0);
                            SharedPreferences.Editor editor = sPref.edit();
                            editor.putString("id", String.valueOf(statusApi.getId()));
                            editor.putString("login", login);
                            editor.putString("pass", password);
                            editor.putString("name", statusApi.getName());
                            editor.putString("build", statusApi.getBuild());
                            editor.putString("room", statusApi.getRoom());
                            editor.putString("phone", statusApi.getPhone());
                            editor.putString("email", statusApi.getEmail());
                            editor.putString("is_auth", "true");


                            editor.commit();


                            Intent auth = new Intent(this, MainPage.class);
                            startActivity(auth);
                        }else if(statusApi.getStatus() == 101){
                            Toast.makeText(this, "Неверный логин либо пароль", Toast.LENGTH_SHORT).show();
                        }else if(statusApi.getStatus() == 400){
                            Toast.makeText(this, "Ошибка сервера!", Toast.LENGTH_SHORT).show();
                        }


                    } catch (MalformedJsonException z){
                        z.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.registrationBtn:
                Intent registration = new Intent(this, RegistrationPage.class);
                startActivity(registration);
                break;
        }
    }
}
