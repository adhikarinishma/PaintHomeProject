package com.example.nishmaadhikari.painthomeproject;

/**
 * Created by nishmaadhikari on 2/7/17.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import yuku.ambilwarna.AmbilWarnaDialog;

import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nishmaadhikari on 1/31/17.
 */

public class Color_Pallete extends Activity {

   public static TextView text1;
    int color = 0xffffff00;
    Button btn;
    Button btn1, btn2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.colour_pallette);


        openDialog(false);

        this.setFinishOnTouchOutside(false);

        text1 = (TextView) findViewById(R.id.textcolor);


        Button btn = (Button) findViewById(R.id.button2);
        Button btn1 = (Button) findViewById(R.id.button3);
        Button btn2 = (Button) findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(false);
            }
        });


    }

    void openDialog(boolean supportsAlpha) {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(Color_Pallete.this, color, supportsAlpha, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
                Color_Pallete.this.color = color;
                   displayColor();



            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

     void displayColor() {
        text1.setText(String.format("0x%08x", color));
        // }
         Intent intent = new Intent(Color_Pallete.this, Start_paint.class);
         String col = text1.getText().toString();
         intent.putExtra("color", col);
    }

     //String col = Integer.toString(color);


}