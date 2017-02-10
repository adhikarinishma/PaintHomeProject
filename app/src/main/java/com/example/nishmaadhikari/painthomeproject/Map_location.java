package com.example.nishmaadhikari.painthomeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by nishmaadhikari on 2/7/17.
 */

public class Map_location extends Fragment {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_location, container, false);
        Button btn1 = (Button) v.findViewById(R.id.mapbutton);
      //  LinearLayout Linearlay= (LinearLayout) v.findViewById(R.id.backlay);

      //  Linearlay.setBackground(getActivity().getResources().getDrawable(R.drawable.map));

       btn1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //  openDialog(false);

                                        startActivity(new Intent(getActivity(), MapsActivity.class));
                                        //   new ColorPickerDialog(getContext(), mPaint.getColor()).show();
                                    }
                                }
        );
         return v;
    }

}