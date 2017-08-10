package com.example.raushan.newway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * Created by Raushan on 05-08-2017.
 */
public class DeviceSelectActivity extends Activity{

    TextView textView1,textView2,textView3,textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);

        Bundle bundle = getIntent().getExtras();
        String CustomerID = bundle.getString("CustomerID");

        textView1 = (TextView) findViewById(R.id.textView4);
        textView2 = (TextView) findViewById(R.id.textView5);
        textView3 = (TextView) findViewById(R.id.textView7);
        textView4 = (TextView) findViewById(R.id.textView9);


        FileInputStream fis;
        final StringBuffer storedString = new StringBuffer();

        try {
            fis = openFileInput("dataCustomer");
            DataInputStream dataIO = new DataInputStream(fis);
            String strLine = null;

            if ((strLine = dataIO.readLine()) != null) {
                storedString.append(strLine);
            }

            dataIO.close();
            fis.close();

            JSONArray inputArray = new JSONArray(strLine);
            for(int i=0;i<inputArray.length();i++) {
                JSONObject jo = inputArray.getJSONObject(i);

                String customerID = jo.getString("CustomerID");

                if (customerID.equalsIgnoreCase(CustomerID)) {
                    textView1.setText("Hello " + jo.getString("Name") + ", we are glad to tell you that below Loans/Services/Facilities can be availed by you");
                    textView2.setText(jo.getString("Loans"));
                    textView3.setText(jo.getString("Services"));
                    textView4.setText(jo.getString("Facilities"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
