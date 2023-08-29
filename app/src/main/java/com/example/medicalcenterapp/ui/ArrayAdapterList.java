package com.example.medicalcenterapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import models.UserType;

public class ArrayAdapterList extends ArrayAdapter<UserType> {

    private final LayoutInflater inflater;
    private final List<UserType> userTypes;
    public ArrayAdapterList(@NonNull Context context, List<UserType> userTypes) {
        super(context,0, userTypes);
        this.inflater = LayoutInflater.from(context);
        this.userTypes = userTypes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        TextView textView = view.findViewById(android.R.id.text1);
        UserType userType = userTypes.get(position);
        textView.setText(userType.getName()); // Aquí puedes usar el nombre o propiedad adecuada
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        TextView textView = view.findViewById(android.R.id.text1);
        UserType userType = userTypes.get(position);
        textView.setText(userType.getName()); // Aquí puedes usar el nombre o propiedad adecuada
        return view;
    }
}
