package com.example.raushan.newway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Handler;
import android.provider.Settings;

import java.util.logging.LogRecord;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Runnable r = new Runnable() {
            @Override
            public void run() {

                String filename = "dataCustomer";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

                    JSONArray jsonArray = new JSONArray();

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("CustomerID", "123456");
                    jsonObject.put("Password", "123456");
                    jsonObject.put("Name", "XYZ");
                    jsonObject.put("Loans", "Personal Loan upto 1 lakhs\nHome Loan upto 5 lakhs \nCar Loan upto 3 lakhs");
                    jsonObject.put("Services", "eStatement\n" + "Cheque\n" + "IMPS/NEFT Transfer\nOthers");
                    jsonObject.put("Facilities", "Locker Facility (with minimum Cost)*\n" + "Foreign Currency Exchange\nConsultancy\nPrivate banking");


                    JSONObject jsonObject1 = new JSONObject();

                    jsonObject1.put("CustomerID", "12345");
                    jsonObject1.put("Password", "12345");
                    jsonObject1.put("Name", "ABC");
                    jsonObject1.put("Loans", "Personal Loan upto 5 lakhs\nHome Loan upto 20 lakhs \nCar Loan upto 10 lakhs");
                    jsonObject1.put("Services", "eStatement\n" + "Cheque\n" + "IMPS/NEFT Transfer\n"+"Others");
                    jsonObject1.put("Facilities", "Locker Facility ( free of Cost ) *\n" + "Foreign Currency Exchange\n"+"Consultancy\n"+"Private banking");


                    jsonArray.put(jsonObject);
                    jsonArray.put(jsonObject1);

                    String data = jsonArray.toString();

                    outputStream.write(data.getBytes());
                    outputStream.close();

                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);

                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Handler h = new Handler();

        h.postDelayed(r, 1500);

    }
}
