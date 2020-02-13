package com.marcorp.first;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import uk.me.hardill.volley.multipart.MultipartRequest;
import uk.me.hardill.volley.multipart.MultipartRequest.*;

public class MainActivity extends AppCompatActivity {

    private TextView textViewPostOutput;
    private Button buttonAddItem;
    private EditText editTextEaNumber;
    private Button buttonGetKitchenItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        addListener1();
        addListener2();

    }

    private void bindViews(){
        buttonAddItem = findViewById(R.id.buttonAddItem);
        editTextEaNumber = findViewById(R.id.ea_number);
        buttonGetKitchenItems = findViewById(R.id.buttonGetKitchenItems);
        textViewPostOutput = findViewById(R.id.textView);
    }

        private void addListener1() {
            buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToKitchen();
            }
        });
    }

    private void addListener2(){
        buttonGetKitchenItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO - Add DELETE method here.
            }
        });
    }


    private void simpleRequest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://kitchen-help.herokuapp.com/kitchen";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        textViewPostOutput.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        queue.add(jsonArrayRequest);
    }

    //"https://kitchen-help.herokuapp.com/kitchen"

/*    private void addItemToKitchen() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://kitchen-help.herokuapp.com/kitchen";

        MultipartRequest request = new MultipartRequest(url, null,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            String stringRes = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                            textViewPostOutput.setText("Response: " + stringRes);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        request.addPart(new FormPart("ean_number", editTextEaNumber.getText().toString()));

        queue.add(request);
    }*/

    private void addItemToKitchen() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://kitchen-help.herokuapp.com/kitchen";

        MultipartRequest request = new MultipartRequest(url, null,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            String stringRes = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                            Toast toast = Toast.makeText(getApplicationContext(), stringRes, Toast.LENGTH_SHORT);
                            toast.show();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO
                    }
                });

        request.addPart(new FormPart("ean_number", editTextEaNumber.getText().toString()));

        queue.add(request);
    }

    private void hardestRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://kitchen-help.herokuapp.com/products";

        MultipartRequest request = new MultipartRequest(url, null,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            String stringRes = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                            textViewPostOutput.setText("Response: " + stringRes);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });



        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_yoggi);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();


        queue.add(request);
    }


}
