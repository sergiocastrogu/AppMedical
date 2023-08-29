package com.example.medicalcenterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import com.example.medicalcenterapp.ui.ArrayAdapterList;
import com.example.medicalcenterapp.ui.ArrayAdapterListUser;

import java.util.List;

import Services.UserService;
import models.ResponseBase;
import models.User;
import models.UserListResponse;
import models.UserType;

public class MedicActivity extends AppCompatActivity {


    Spinner medicList;
    Spinner userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic);

        Button backBtn = (Button) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MedicActivity.this, MainActivity.class);
            startActivity(intent);
        });
        getMedicList();
        getUserList();
    }

    private void getMedicList(){
        this.medicList = (Spinner) findViewById(R.id.lstMedic);
        UserService userService = new UserService();
        userService.getUserByUserType(this, 1, new UserService.VolleyCallback<ResponseBase<UserListResponse>>() {
            @Override
            public void onSuccess(ResponseBase<UserListResponse> data, Context context) {
                List<User> userListMedic = data.getData().getUsers();
                ArrayAdapterListUser arrayAdapterList = new ArrayAdapterListUser(context, userListMedic);
                medicList.setAdapter(arrayAdapterList);
            }

            @Override
            public void onError() {

            }
        });
    }

    private void getUserList(){
        this.userList = (Spinner) findViewById(R.id.lstUserType);
        UserService userService = new UserService();
        userService.getUserByUserType(this, 2, new UserService.VolleyCallback<ResponseBase<UserListResponse>>() {
            @Override
            public void onSuccess(ResponseBase<UserListResponse> data, Context context) {
                List<User> userListResponse = data.getData().getUsers();
                ArrayAdapterListUser arrayAdapterList = new ArrayAdapterListUser(context, userListResponse);
                userList.setAdapter(arrayAdapterList);
            }

            @Override
            public void onError() {

            }
        });
    }
}