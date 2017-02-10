package com.example.nishmaadhikari.painthomeproject;

/**
 * Created by nishmaadhikari on 2/7/17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.content.Context;
import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.widget.AmbilWarnaPreference;
import com.example.nishmaadhikari.painthomeproject.Color_Pallete;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Start_paint extends Activity {
    private Paint mPaint;
    private View homeView;
    String json_string;
    int color = 0xffffff00;
    String Surface;
    String Paint_type;


    EditText wall_height;
    EditText wall_width;
    EditText window_height;
    EditText window_width;
    EditText door_height;
    EditText door_width;

    Button btn;
    TextView text1;
    String color_code;



    //  TextView text2 = (TextView) findViewById(R.id.text1);

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.paint_start);

        openDialog(false);

        this.setFinishOnTouchOutside(false);


        // String col1=getActivity().getIntent().getStringExtra("color");
        // TextView text=(TextView) homeView.findViewById(R.id.texthex);
        //  text.setText(col1);

        text1 = (TextView) findViewById(R.id.texthex);
        color_code=text1.getText().toString();

        wall_height=(EditText)findViewById(R.id.wheight);
        final String wall_height1=wall_height.getText().toString();
        wall_width=(EditText)findViewById(R.id.wwidth);
        final String wall_width1=wall_width.getText().toString();
        window_height=(EditText)findViewById(R.id.winheight);
        final String window_height1=window_height.getText().toString();
        window_width=(EditText)findViewById(R.id.winwidth);
        final String window_width1=window_width.getText().toString();
        door_height=(EditText)findViewById(R.id.dheight);
        final String door_height1=door_height.getText().toString();
        door_width=(EditText)findViewById(R.id.dwidth);
        final String door_width1=door_width.getText().toString();





        // btn1.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //  public void onClick(View v) {
        //  openDialog(false);

        //      startActivity(new Intent(getActivity(), Color_Pallete.class));
        //   new ColorPickerDialog(getContext(), mPaint.getColor()).show();

        //   }
        //  });

        new BackgroundTask().execute();

        Button btn2 = (Button) findViewById(R.id.calculate);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(json_string==null){
                Toast.makeText(getApplicationContext(),"Loading....",Toast.LENGTH_SHORT).show();
            }
                else{
                Intent intent = new Intent (Start_paint.this,Calculated_output.class);
                intent.putExtra("json_data",json_string);
                intent.putExtra("color_code",color_code);
                intent.putExtra("wall_height",wall_height1);
                intent.putExtra("wall_width",wall_width1);
                intent.putExtra("win_height",window_height1);
                intent.putExtra("win_width",window_width1);
                intent.putExtra("door_height",door_height1);
                intent.putExtra("door_width",door_width1);
                intent.putExtra("surface",Surface);
                intent.putExtra("paint_type",Paint_type);
                startActivity(intent);
            }


            }
        });


        String[] values2 =
                {"Rough", "Smooth", "Paris"};
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Start_paint.this, android.R.layout.simple_spinner_item, values2);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(adapter);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Surface = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String[] values1 =
                {"Primer", "Distemper", "Enamel"};
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Start_paint.this, android.R.layout.simple_spinner_item, values1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Paint_type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void openDialog(boolean supportsAlpha) {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(Start_paint.this, color, supportsAlpha, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
                Start_paint.this.color = color;
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

    }
    public class BackgroundTask extends AsyncTask<Void,Void,String>{

        String json_url;

        @Override
        protected  void onPreExecute(){
            json_url="http://paint.bidheegroup.com";
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                URL  url = new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream= httpURLConnection.getInputStream();

                BufferedReader reader = null;
                StringBuffer response = new StringBuffer();
                try {
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return response.toString();

//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                StringBuilder stringBuilder= new StringBuilder();
//
//                while ((json_string=bufferedReader.readLine())!=null){
//                    stringBuilder.append(json_string+"\n");
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return stringBuilder.toString().trim();

            }catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values){super.onProgressUpdate(values);}

        @Override
        protected void onPostExecute(String result){
            json_string=result;
            Log.i("Status",json_string);
        }
    }


}




