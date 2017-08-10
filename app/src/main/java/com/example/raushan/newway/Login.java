package com.example.raushan.newway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Raushan on 05-08-2017.
 */
public class Login extends Activity {

    EditText editText1,editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void scanActivity(View view) {

        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        String user = editText1.getText().toString();
        String password = editText2.getText().toString();

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

            boolean flag = true;

            JSONArray inputArray = new JSONArray(strLine);
            for (int i = 0; i < inputArray.length(); i++) {

                JSONObject jo = inputArray.getJSONObject(i);

                String customerID = jo.getString("CustomerID");
                String Password = jo.getString("Password");

                if (customerID.equalsIgnoreCase(user) && Password.equalsIgnoreCase(password)) {

                    flag = false;

                    Intent intent = new Intent(Login.this, DeviceScanActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("CustomerID", customerID);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            if(flag==true) {
                Toast.makeText(this, "Wrong Details", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
