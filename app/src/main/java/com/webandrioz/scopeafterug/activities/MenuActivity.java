package com.webandrioz.scopeafterug.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.constants.Constants;

public class MenuActivity extends BaseActivity {
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        id=getIntent().getStringExtra("id");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button books= (Button) findViewById(R.id.books);
        Button coachings= (Button) findViewById(R.id.coachings);
        Button papers= (Button) findViewById(R.id.paper);
        Button quiz= (Button) findViewById(R.id.quiz);
        Button eligibility= (Button) findViewById(R.id.eligibility);
        eligibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuActivity.this, WebViewActivity.class);
                i.putExtra("pdf", Constants.BASE_URL_PDF+"eligibility/"+id+".pdf");
               startActivity(i);
            }
        });
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MenuActivity.this,BooksActivity.class);
                in.putExtra("id",id);
                startActivity(in);

            }
        });
        coachings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MenuActivity.this,CoachingsActivity.class);
                in.putExtra("id",id);
                startActivity(in);

            }
        });
        papers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MenuActivity.this,PaperActivity.class);
                in.putExtra("id",id);
                startActivity(in);

            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MenuActivity.this,QUIZActivity.class);
                in.putExtra("id",id);
                startActivity(in);

            }
        });
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
