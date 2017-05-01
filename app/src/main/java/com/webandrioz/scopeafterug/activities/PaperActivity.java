package com.webandrioz.scopeafterug.activities;

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
import com.webandrioz.scopeafterug.adapters.PaperListViewAdapter;
import com.webandrioz.scopeafterug.constants.Constants;
import com.webandrioz.scopeafterug.models.Paper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;

public class PaperActivity extends BaseActivity  implements DownloadFile.Listener {

    private  final String TAG = getClass().getName();
    ListView listView;
    ArrayList<Paper> papers=new ArrayList<>();
    RemotePDFViewPager remotePDFViewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView= (ListView) findViewById(R.id.paperListView);
        getSupportActionBar().setTitle("Papers");

        getPapers(getIntent().getStringExtra("id"));
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Constants.BASE_URL+Constants.PAPERS+books.get(position).getPaperId()+".pdf"4
//                Log.e(TAG, "onItemClick: "+Constants.BASE_URL+Constants.PAPERS+papers.get(position).getPaperId()+".pdf" );
//                 remotePDFViewPage =
//                        new RemotePDFViewPager(PaperActivity.this,  Constants.BASE_URL+Constants.PAPERS+papers.get(position).getPaperId()+".pdf", PaperActivity.this);
//            }
//        });
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
    public void getPapers(final String id){
        String REGISTER_URL= Constants.BASE_URL+ Constants.PAPER_URL;
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
                                JSONArray jsonArray=jsonObject.getJSONArray("papers");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    papers.add(new Paper(jsonObject1.getString("paper_id"),jsonObject1.getString("paper_name")));
                                }

                                listView.setAdapter(new PaperListViewAdapter(PaperActivity.this,papers));
                            }else{
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PaperActivity.this, "Some Problem Occur Try Again!!", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onResponse: "+e.toString() );
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PaperActivity.this,error.toString(),Toast.LENGTH_LONG).show();
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

    @Override
    public void onSuccess(String url, String destinationPath) {
        PDFPagerAdapter adapter = new PDFPagerAdapter(this, "AdobeXMLFormsSamples.pdf");
        remotePDFViewPage.setAdapter(adapter);
        setContentView(remotePDFViewPage);
    }

    @Override
    public void onFailure(Exception e) {
        Log.e(TAG, "onFailure: "+e.getMessage() );
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        Log.e(TAG, "onProgressUpdate: "+progress );

    }
}
