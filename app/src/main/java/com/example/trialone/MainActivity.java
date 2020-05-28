package com.example.trialone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void userAuthentication(View view) {
        //Intent intent = new Intent(this, UserWelcome.class);

        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.pasword)).getText().toString();

        try {
            String result = new AuthenticationTask().execute(username, password).get();
            if (result.equals("1")) {
                Intent intent = new Intent(this, UserWelcome.class);
                startActivity(intent);
            } else if(result.equals("2")) {
                ((TextView)findViewById(R.id.message)).setText("Invalid Username/Password");
                ((TextView)findViewById(R.id.message)).setVisibility(View.VISIBLE);
            } else {
                ((TextView)findViewById(R.id.message)).setText("Something went wrongsa try again"+result);
                ((TextView)findViewById(R.id.message)).setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            ((TextView)findViewById(R.id.message)).setText("Something went wrongs try again"+ e);
            ((TextView)findViewById(R.id.message)).setVisibility(View.VISIBLE);

        }

        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);
    }

    private class AuthenticationTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... input) {

            try {
                String username = input[0];
                String password = input[1];

                URL url = new URL("http://192.168.0.105:8080/supplymgmt/users/authenticate");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                String body = "{\"username\": \"" + username + "\",\"password\":\"" + password + "\"}";
                OutputStream out = conn.getOutputStream();
                out.write(body.getBytes());
                out.flush();
                out.close();
                if (conn.getResponseCode() == 200) {
                    conn.disconnect();
                    return "1";
                } else if(conn.getResponseCode() == 401) {
                    conn.disconnect();
                    return "2";
                } else {
                    conn.disconnect();
                    return conn.getResponseCode()+"";
                }
            } catch (Exception e) {
                return e.toString();
            }
        }
    }
}
