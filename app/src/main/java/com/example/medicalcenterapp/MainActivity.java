package com.example.medicalcenterapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.medicalcenterapp.databinding.ActivityMainBinding;

import java.util.List;

import Services.UserService;
import models.MedicalAppointment;
import models.ResponseBase;
import models.ResultPost;
import models.User;
import models.UserListResponse;
import models.UserResponse;
import repositories.MedicalAppointmentRepository;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MedicalAppointmentRepository rep = new MedicalAppointmentRepository();
        List<MedicalAppointment> medicalAppointmentList = rep.getMedicalAppointmentList();
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Button button = (Button) findViewById(R.id.user_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        Button buttonmedical = (Button) findViewById(R.id.medical_btn);
        buttonmedical.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicActivity.class);
                startActivity(intent);
            }
        });
    }


    private void getUserById(){
        UserService userService = new UserService();
        userService.getUserById(this, 1, new UserService.VolleyCallback<ResponseBase<UserResponse>>() {
            @Override
            public void onSuccess(ResponseBase<UserResponse> data, Context context) {
                User u = data.getData().getUser();
                System.out.println("Salida user");
            }
            @Override
            public void onError() {

            }
        });
    }

    private void getUserByType(int userType){
        UserService userService = new UserService();
        userService.getUserByUserType(this, userType, new UserService.VolleyCallback<ResponseBase<UserListResponse>>() {
            @Override
            public void onSuccess(ResponseBase<UserListResponse> data, Context context) {
                List<User> userList = data.getData().getUsers();
                System.out.println("Salida user");
            }

            @Override
            public void onError() {

            }
        });
    }

}