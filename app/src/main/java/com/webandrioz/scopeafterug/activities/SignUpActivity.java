package com.webandrioz.scopeafterug.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.constants.Constants;
import com.webandrioz.scopeafterug.utils.SharedPreferenceProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class SignUpActivity extends AppCompatActivity {
    public final String TAG = getClass().getName();
    SpotsDialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimations();

        setContentView(R.layout.activity_sign_up);
        dialog = new SpotsDialog(SignUpActivity.this);
//        dialog.setCancelable(false);

        final EditText name= (EditText) findViewById(R.id.name);
        final EditText email= (EditText) findViewById(R.id.email);
        final EditText password= (EditText) findViewById(R.id.password);
        final Button signUp= (Button) findViewById(R.id.signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() || password.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
                    Toast.makeText(SignUpActivity.this, "All Field Is Mandatory.", Toast.LENGTH_SHORT).show(); 
                }
                else {
                    signUpcall(name.getText().toString(), email.getText().toString(),password.getText().toString());
                }
            }
        });

    }
    private void setupWindowAnimations() {
        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
        getWindow().setEnterTransition(fade);

    }


    public void signUpcall(final String name, final String email, final String password){
        String REGISTER_URL= Constants.BASE_URL+ Constants.CUSTOM_SIGNUP_URL;
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("success").equals("1")){
                                new SharedPreferenceProvider().storeData(SignUpActivity.this,"USER_NAME",name);
                                new SharedPreferenceProvider().storeData(SignUpActivity.this,"LOGIN","1");
                                new SharedPreferenceProvider().storeData(SignUpActivity.this,"USER_EMIAL",email);
                                dialog.dismiss();
                                Toast.makeText(SignUpActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                Intent in=new Intent(SignUpActivity.this,DomainActivity.class);
                                startActivity(in);
                                finish();
                            }else{
                                Toast.makeText(SignUpActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("password",password);
                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }

