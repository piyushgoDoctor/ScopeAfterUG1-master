package com.webandrioz.scopeafterug.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.adapters.ExamListViewAdapter;
import com.webandrioz.scopeafterug.constants.Constants;
import com.webandrioz.scopeafterug.models.Exams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExamsActivity extends BaseActivity {
    private  final String TAG =getClass().getName() ;
    ListView listView;
    String id;
    ArrayList<Exams> exams=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        listView= (ListView) findViewById(R.id.examListView);
        id=getIntent().getStringExtra("id");
        getExmas(id);
        Log.e(TAG, "getExmas: "+id );
        getSupportActionBar().setTitle("Exams");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


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

    public void getExmas(final String id){
        String REGISTER_URL= Constants.BASE_URL+ Constants.EXAMS_URL;
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            dialog.dismiss();
                            JSONObject jsonObject=new JSONObject(response);
//                            if(jsonObject.getString("success").endsWith("1")){
//
//                            JSONObject jsonObject1=jsonObject.getJSONObject("exams");
                            JSONArray jsonArray=jsonObject.getJSONArray("exams");
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                exams.add(new Exams(jsonObject1.getString("exam_id"),jsonObject1.getString("exam_name"),jsonObject1.getString("about")));

                            }
                            listView.setAdapter(new ExamListViewAdapter(ExamsActivity.this,exams));
//
//                            }else{
//                                Toast.makeText(ExamsActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
//                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, e.toString() );
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ExamsActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
//                params.put("password",password);
//                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
