package com.example.raisul.mess_management;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Meal_Management_Fragment extends android.support.v4.app.Fragment {

    View view;

    public Meal_Management_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.meal_management, container,false);
        return view;
    }
}
