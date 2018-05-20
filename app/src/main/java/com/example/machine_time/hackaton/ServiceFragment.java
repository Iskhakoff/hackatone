package com.example.machine_time.hackaton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ServiceFragment extends Fragment{

    Spinner spinner;
    EditText editProblem, editDate;
    Button btnSend;



    final String URL = "http://maxim-zubarev.esy.es/porsche/api_pr/";

    Gson gson = new GsonBuilder().create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    Link auth = retrofit.create(Link.class);

    SharedPreferences sPref;
    int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.service_fragment, container, false);

        getActivity().setTitle("Заполнить заявку");

        editProblem = (EditText)v.findViewById(R.id.editProblem);
        editDate = (EditText)v.findViewById(R.id.editDate);
        btnSend = (Button)v.findViewById(R.id.btnSend);

        spinner = (Spinner)v.findViewById(R.id.spinner);

        sPref = getActivity().getSharedPreferences("savedData", 0);
        id = Integer.parseInt(sPref.getString("id", ""));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Services services = new Services();
                services.setType(spinner.getSelectedItem().toString());
                services.setInfo(editProblem.getText().toString());
                services.setDate(editDate.getText().toString());
                services.setUser_id(String.valueOf(id));


                String jsonReq = gson.toJson(services);

                System.out.println("submited json" + jsonReq);

                Call<Object> call = auth.services(services);

                try {

                    Response<Object> response = call.execute();

                    System.out.println(response.body());

                    StatusApi statusApi = gson.fromJson(response.body().toString(), StatusApi.class);

                    if(statusApi.getStatus() == 200)
                    {
                        Toast.makeText(getActivity(), "Заявка отправлена!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Произошла ошибка!", Toast.LENGTH_SHORT).show();
                    }


                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return v;

    }
}
