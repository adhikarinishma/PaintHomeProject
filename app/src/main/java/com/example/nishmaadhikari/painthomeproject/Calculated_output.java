package com.example.nishmaadhikari.painthomeproject;

/**
 * Created by nishmaadhikari on 2/7/17.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Calculated_output extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String Wall_height,Wall_width,window_height,window_width,Door_height,Door_width,Color_code,Surface,Paint_type;
    EditText Paint_cost,Paint_amount;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculated_output);

        Paint_cost=(EditText)findViewById(R.id.paint_cost);
        Paint_amount=(EditText)findViewById(R.id.paint_amount);

        json_string=getIntent().getStringExtra("json_data");
        Color_code=getIntent().getStringExtra("color_code");
        Wall_height=getIntent().getStringExtra("wall_height");
        double w_height1=Double.parseDouble(Wall_height);
        Wall_width=getIntent().getStringExtra("wall_width");
        double w_width1=Double.parseDouble(Wall_width);
        window_height=getIntent().getStringExtra("win_height");
        double win_height1=Double.parseDouble(window_height);
        window_width=getIntent().getStringExtra("win_width");
        double win_width1=Double.parseDouble(window_width);
        Door_height=getIntent().getStringExtra("door_height");
        double door_height1=Double.parseDouble(Door_height);
        Door_width=getIntent().getStringExtra("door_width");
        double door_width1=Double.parseDouble(Door_width);
        Surface=getIntent().getStringExtra("surface");
        Paint_type=getIntent().getStringExtra("paint_type");

        try{
            jsonArray = new JSONArray(json_string);

            String color,surface1,paint_type1,cost,amount;


                for(int i =0; i < jsonArray.length();i++) {
                    JSONObject JO = jsonArray.getJSONObject(i);
                    color = JO.getString("color");
                    surface1 = JO.getString("surface");
                    paint_type1 = JO.getString("paint_type");
                    cost = JO.getString("cost");
                    amount = JO.getString("amount");


                    if (Color_code == color && Surface == surface1 && Paint_type == paint_type1) {
                        double amt = Double.parseDouble(amount);
                        double cst = Double.parseDouble(cost);
                        double area = (w_height1 * w_width1) - ((win_height1 * win_width1) + (door_height1 * door_width1));

                        if (area > 0) {
                            double amount_total = area * amt;
                            double cost_total = amount_total * cst;
                            Paint_amount.setText(String.valueOf(amount_total) + " lts");
                            Paint_cost.setText("Rs. " + String.valueOf(cost_total));

                        } else {
                            Toast.makeText(getApplicationContext(), "Please enter valid width and height", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
