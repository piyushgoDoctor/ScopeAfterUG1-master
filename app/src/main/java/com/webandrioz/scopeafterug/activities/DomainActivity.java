package com.webandrioz.scopeafterug.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.activities.chat.ChatActivity;
import com.webandrioz.scopeafterug.adapters.DomainGridAdapter;
import com.webandrioz.scopeafterug.constants.Constants;
import com.webandrioz.scopeafterug.models.Domains;
import com.webandrioz.scopeafterug.utils.SharedPreferenceProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DomainActivity extends BaseActivity {
    private  final String TAG = getClass().getName();
    GridView gridView;
    ArrayList<Domains> domain=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain);
        try {
//            getSupportActionBar().hide();
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }catch (NullPointerException e){
            Log.e(TAG, "onCreate: "+e.getMessage() );
        }
//      overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

        gridView= (GridView) findViewById(R.id.domainGridView);
        getDomain();
        FloatingActionButton chat= (FloatingActionButton) findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(new SharedPreferenceProvider().fatchDataLegislators(DomainActivity.this,"LOGIN").equals("0"))) {
                    startActivity(new Intent(DomainActivity.this, ChatActivity.class));
                }else {
                    Toast.makeText(DomainActivity.this, "To Access Chat You Must LOGIN", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void getDomain(){
        dialog.show();
        String REGISTER_URL= Constants.BASE_URL+ Constants.DOMAIN_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("success").equals("1")){
                                 dialog.dismiss();
                                JSONArray jsonArray=jsonObject.getJSONArray("domain");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    domain.add(new Domains(jsonObject1.getString("domain_name"),jsonObject1.getString("domain_id")));
                                }

                                gridView.setAdapter(new DomainGridAdapter(DomainActivity.this,domain));
                            }else{
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DomainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onErrorResponse: "+error.toString());
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
//                /params.put("name",name);
//                params.put("password",password);
//                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.domaim_manu, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.logout:
//                new SharedPreferenceProvider().deleteData(DomainActivity.this);
//
//                return true;
//            case R.id.feedback:
//                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                emailIntent.setType("vnd.android.cursor.item/email");
//                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"scopeafterug@gmail.com"});
//                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback");
//                startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
//                return true;
//            case R.id.rate:
////                startActivity(new Intent(this, Help.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
