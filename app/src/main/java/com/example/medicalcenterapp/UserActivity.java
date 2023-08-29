package com.example.medicalcenterapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.medicalcenterapp.ui.ArrayAdapterList;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Services.UserService;
import Services.UserTypeService;
import models.ResponseBase;
import models.ResultPost;
import models.User;
import models.UserType;
import models.UserTypeListResponse;

public class UserActivity extends AppCompatActivity {

    private Spinner userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Button backBtn = (Button) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(UserActivity.this, MainActivity.class);
            startActivity(intent);
        });
        this.userList = (Spinner) findViewById(R.id.lstUserType);
        UserTypeService userTypeService = new UserTypeService();
        userTypeService.getUserByUserType(this, new UserTypeService.VolleyCallback<ResponseBase<UserTypeListResponse>>() {
            @Override
            public void onSuccess(ResponseBase<UserTypeListResponse> data, Context context) {
                List<UserType> userTypes = data.getData().getUserTypes();
                ArrayAdapterList arrayAdapterList = new ArrayAdapterList(context, userTypes);
                userList.setAdapter(arrayAdapterList);
            }

            @Override
            public void onError() {
                Toast.makeText(UserActivity.this, "Error al consultar tipos de usuario", Toast.LENGTH_SHORT).show();
            }
        });



        Button btnCreate = (Button) findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(view -> {
            EditText txtName = (EditText) findViewById(R.id.txtName);
            EditText txtLastName = (EditText) findViewById(R.id.txtLastName);
            EditText txtBithDate = (EditText) findViewById(R.id.txtBirthDay);
            User u = new User();
            u.setFirstName(txtName.getText().toString());
            u.setLastName(txtLastName.getText().toString());
            String date = txtBithDate.getText().toString();
            try{
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato de entrada
                Date parsedDate = inputFormat.parse(date);
                u.setBirthDate(parsedDate);
            } catch (Exception ex){
                Toast.makeText(this, "Error en formato de fechas", Toast.LENGTH_SHORT).show();
            }
            u.setActive(true);
            Spinner spnUserType = findViewById(R.id.lstUserType);
            UserType selectedUserType = (UserType) spnUserType.getSelectedItem();
            u.setTypeId(selectedUserType.getId());
            createUser(u);
        });
    }


    private void createUser(User user){

        UserService userService = new UserService();
        userService.createUser(this, user, new UserService.VolleyCallback<ResponseBase<ResultPost>>() {
            @Override
            public void onSuccess(ResponseBase<ResultPost> data, Context context) {
                int response = data.getData().getId();
                if(response == 0 ){
                    Toast.makeText(UserActivity.this, "Usuario creado",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UserActivity.this, "Usuario no creado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}