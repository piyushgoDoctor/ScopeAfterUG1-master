package com.webandrioz.scopeafterug.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.activities.chat.ChatRoom;
import com.webandrioz.scopeafterug.models.Chat;
import com.webandrioz.scopeafterug.utils.SharedPreferenceProvider;

import java.util.ArrayList;

/**
 * Created by gipsy_danger on 26/3/17.
 */

public class ChatAdapter extends ArrayAdapter<String> {
    Context con;
    ArrayList<Chat> chats=new ArrayList<>();

    public ChatAdapter(ChatRoom examsActivity, ArrayList<Chat> exams) {
        super(examsActivity, R.layout.chat_layout);
        con=examsActivity;
        chats=exams;
    }

    @Override
    public int getCount() {
        return chats.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return chats.get(position).getName();
    }

//    @Override
//    public int getPosition(@Nullable String item) {
//        return super.getPosition(item);
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater li= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =li.inflate(R.layout.chat_layout,null);
        String userName=new SharedPreferenceProvider().fatchDataLegislators(con,"USER_NAME");
        TextView guest= (TextView) convertView.findViewById(R.id.guestTextView);
        TextView self= (TextView) convertView.findViewById(R.id.selfTextView);
        if(chats.get(position).getName().equals(userName)){
            self.setText(chats.get(position).getName()+"\n"+chats.get(position).getMsg());
            guest.setVisibility(View.GONE);
        }else {
            self.setVisibility(View.GONE);
            guest.setText(chats.get(position).getName()+"\n"+chats.get(position).getMsg());

        }




        return convertView;
    }
}
