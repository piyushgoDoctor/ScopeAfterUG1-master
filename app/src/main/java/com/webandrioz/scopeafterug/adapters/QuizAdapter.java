package com.webandrioz.scopeafterug.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.webandrioz.scopeafterug.activities.QUIZActivity;
import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.models.Quiz;

import java.util.ArrayList;

/**
 * Created by godoctor on 27/2/17.
 */

public class QuizAdapter extends ArrayAdapter<String> {
    Context con;
    ArrayList<Quiz> books=new ArrayList<>();

    public QuizAdapter(QUIZActivity examsActivity, ArrayList<Quiz> exams) {
        super(examsActivity, R.layout.quiz_listview_layout);
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
        return books.get(position).getAns();
    }

//    @Override
//    public int getPosition(@Nullable String item) {
//        return super.getPosition(item);
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater li= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =li.inflate(R.layout.quiz_listview_layout,null);
        TextView ques= (TextView) convertView.findViewById(R.id.question);
        TextView opA= (TextView) convertView.findViewById(R.id.opA);
        TextView opB= (TextView) convertView.findViewById(R.id.opB);
        TextView opC= (TextView) convertView.findViewById(R.id.opC);
        TextView opD= (TextView) convertView.findViewById(R.id.opD);
        final TextView ans= (TextView) convertView.findViewById(R.id.ans);
        ques.setText("Question:-"+books.get(position).getQues());
        opA.setText("Option A-\n"+books.get(position).getOpA());
        opB.setText("Option B-\n"+books.get(position).getOpB());
        opC.setText("Option C-\n"+books.get(position).getOpC());
        opD.setText("Option D-\n"+books.get(position).getOpD());
        ans.setText("Show Ans");
        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans.setText("Answer is\n"+books.get(position).getAns());
                ans.setTextColor(Color.parseColor("#3F51B5"));

            }
        });


        return convertView;
    }
}

