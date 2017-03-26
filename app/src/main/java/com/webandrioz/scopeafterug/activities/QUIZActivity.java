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
import com.webandrioz.scopeafterug.adapters.QuizAdapter;
import com.webandrioz.scopeafterug.constants.Constants;
import com.webandrioz.scopeafterug.models.Quiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QUIZActivity extends BaseActivity {
    private  final String TAG = getClass().getName();
    ListView listView;
    ArrayList<Quiz> quizs=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView= (ListView) findViewById(R.id.quizListView);
        getSupportActionBar().setTitle("Quiz");
        getQuiz(getIntent().getStringExtra("id"));
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
    public void getQuiz(final String id){
        String REGISTER_URL= Constants.BASE_URL+ Constants.QUIZ_URL;
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("success").equals("1")){
                                dialog.dismiss();
                                JSONArray jsonArray=jsonObject.getJSONArray("quiz");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    quizs.add(new Quiz(jsonObject1.getString("question_id"),jsonObject1.getString("question"),jsonObject1.getString("option_a"),jsonObject1.getString("option_b"),jsonObject1.getString("option_c"),jsonObject1.getString("option_d"),jsonObject1.getString("answer"),jsonObject1.getString("author") ));
                                }

                                listView.setAdapter(new QuizAdapter(QUIZActivity.this,quizs));
                            }else{
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "onResponse: "+e.toString() );
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(QUIZActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
//                /params.put("name",name);
                params.put("id",id);
//                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

