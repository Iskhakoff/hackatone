package com.example.machine_time.hackaton;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    TextView name, login, campus, room, phone, email;
    Button exitBtn;

    SharedPreferences sPref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment, container, false);

        sPref = getActivity().getSharedPreferences("savedData", 0);

        getActivity().setTitle("Профиль");

        name = (TextView)v.findViewById(R.id.name);
        login = (TextView)v.findViewById(R.id.login);
        campus = (TextView)v.findViewById(R.id.campus);
        room = (TextView)v.findViewById(R.id.room);
        phone = (TextView)v.findViewById(R.id.phone);
        email = (TextView)v.findViewById(R.id.email);
        exitBtn = (Button)v.findViewById(R.id.exitBtn);

        name.setText(sPref.getString("name", ""));
        login.setText(sPref.getString("login", ""));
        campus.setText(sPref.getString("build", ""));
        room.setText(sPref.getString("room", ""));
        phone.setText(sPref.getString("phone", ""));
        email.setText(sPref.getString("email", ""));

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPref.edit().clear().apply();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
