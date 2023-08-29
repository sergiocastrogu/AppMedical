package com.example.medicalcenterapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import models.User;
import models.UserType;

public class ArrayAdapterListUser extends ArrayAdapter<User> {

    private final LayoutInflater inflater;
    private final List<User> user;
    public ArrayAdapterListUser(@NonNull Context context, List<User> user) {
        super(context,0, user);
        this.inflater = LayoutInflater.from(context);
        this.user = user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        TextView textView = view.findViewById(android.R.id.text1);
        User _user = user.get(position);
        textView.setText(_user.getFirstName()); // Aquí puedes usar el nombre o propiedad adecuada
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        TextView textView = view.findViewById(android.R.id.text1);
        User _user = user.get(position);
        textView.setText(_user.getFirstName()); // Aquí puedes usar el nombre o propiedad adecuada
        return view;
    }
}
