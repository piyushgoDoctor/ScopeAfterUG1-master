package com.webandrioz.scopeafterug.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.activities.chat.ChatActivity;
import com.webandrioz.scopeafterug.utils.SharedPreferenceProvider;

import dmax.dialog.SpotsDialog;

public class BaseActivity extends AppCompatActivity {
    SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new SpotsDialog(BaseActivity.this);
//        dialog.setCancelable(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.domaim_manu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.logout:
                new SharedPreferenceProvider().deleteData(BaseActivity.this);
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                finish();
                return true;
            case R.id.feedback:
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent.setType("vnd.android.cursor.item/email");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"scopeafterug@gmail.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback");
                startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
                return true;
            case R.id.rate:
//                startActivity(new Intent(this, Help.class));
                return true;
            case R.id.gd:
                if(!(new SharedPreferenceProvider().fatchDataLegislators(BaseActivity.this,"LOGIN").equals("0"))) {
                    startActivity(new Intent(BaseActivity.this, ChatActivity.class));
                }else {
                    Toast.makeText(BaseActivity.this, "To Access Chat You Must LOGIN", Toast.LENGTH_SHORT).show();
                }
//                startActivity(new Intent(this, Help.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
