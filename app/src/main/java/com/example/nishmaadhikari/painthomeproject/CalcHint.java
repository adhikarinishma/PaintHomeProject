package com.example.nishmaadhikari.painthomeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by nishmaadhikari on 2/8/17.
 */

public class CalcHint extends Fragment {


    private View homeView;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.calchint, container, false);

        Button btn1 = (Button) homeView.findViewById(R.id.button);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Start_paint.class));

            }

            });


          return homeView;
    }
}
