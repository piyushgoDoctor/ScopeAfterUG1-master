package com.webandrioz.scopeafterug.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.webandrioz.scopeafterug.activities.PaperActivity;
import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.constants.Constants;
import com.webandrioz.scopeafterug.models.Paper;

import java.util.ArrayList;

/**
 * Created by godoctor on 27/2/17.
 */

public class PaperListViewAdapter extends ArrayAdapter<String> {
    Context con;
    ArrayList<Paper> books=new ArrayList<>();

    public PaperListViewAdapter(PaperActivity examsActivity, ArrayList<Paper> exams) {
        super(examsActivity, R.layout.paper_listview_layout);
        con=examsActivity;
        books=exams;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return books.get(position).getPaperId();
    }

//    @Override
//    public int getPosition(@Nullable String item) {
//        return super.getPosition(item);
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater li= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =li.inflate(R.layout.paper_listview_layout,null);
        TextView author= (TextView) convertView.findViewById(R.id.paperName);
        TextView buyonline= (TextView) convertView.findViewById(R.id.openPaper);

        author.setText(books.get(position).getPaperName());
//        author.setText(books.get(position).getAuthor());
        buyonline.setText("CLICK HERE TO OPEN.");
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://docs.google.com/viewer?url="+Constants.BASE_URL+Constants.PAPERS+books.get(position).getPaperId()+".pdf"));
                con.startActivity(i);
            }
        });


        return convertView;
    }
}
