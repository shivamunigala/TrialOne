package com.example.trialone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trialone.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserManagement extends AppCompatActivity {
    List<User> listItems;
    RecyclerView recyclerView;
    MyAdapter adapter;
    private static final String URL_AllUSERS = "http://192.168.0.105:8080/supplymgmt/users/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        recyclerView = findViewById(R.id.UsersData);
//        TextView textView = findViewById(R.id.test);
        listItems=new ArrayList<>();

//        for(int i=0;i<10;i++) {
//            User user = new User();
//            user.setId("saf+");
//            user.setPassword("afaf");
//            user.setName("afafaf");
//            user.setUsername("adfadfad");
//            user.setRole("adfaf");
//            listItems.add(user);
////        }
        loadUsersData();

//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        adapter = new MyAdapter(listItems, getApplicationContext());
//        recyclerView.setAdapter(adapter);

//        textView.setText(listItems.toString());

    }

    private void loadUsersData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ....");
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_AllUSERS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray users = response.getJSONArray("users");
                    progressDialog.dismiss();
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject user = users.getJSONObject(i);
                        User userObj = new User();
                        userObj.setId(user.getString("id"));
                        userObj.setPassword(user.getString("password"));
                        userObj.setName(user.getString("name"));
                        userObj.setUsername(user.getString("username"));
                        userObj.setRole(user.getString("role"));

                        listItems.add(userObj);
                    }
                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new MyAdapter(listItems, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_AllUSERS, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject o = response.getJSONObject(i);
//                        User user = new User();
//                        user.setId(o.getString("id"));
//                        user.setPassword(o.getString("password"));
//                        user.setName(o.getString("name"));
//                        user.setUsername(o.getString("username"));
//                        user.setRole(o.getString("role"));
//
//                        listItems.add(user);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapter = new MyAdapter(listItems, getApplicationContext());
//                recyclerView.setAdapter(adapter);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                progressDialog.dismiss();
////                        Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
//                        TextView textView = findViewById(R.id.test);
//                        textView.setText(error.getMessage());
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);
//    }


//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading ....");
//        progressDialog.show();
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_AllUSERS,
//                new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                   progressDialog.dismiss();
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        JSONArray array = jsonObject.getJSONArray("users");
//                        for(int i=0;i<array.length();i++){
//                            JSONObject o =array.getJSONObject(i);
//                             User user = new User(
//                                o.getString("id"),
//                                o.getString("password"),
//                                o.getString("name"),
//                                o.getString("username"),
//                                o.getString("role")
//                            );
//                            listItems.add(user);
//                        }
////                        textView.setText();
//                        adapter= new MyAdapter(listItems,getApplicationContext());
//                        recyclerView.setAdapter(adapter);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//        });
//
//        requestQueue.add(stringRequest);
//    }
}
