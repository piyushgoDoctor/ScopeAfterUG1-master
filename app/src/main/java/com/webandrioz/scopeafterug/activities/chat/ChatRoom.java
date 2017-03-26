package com.webandrioz.scopeafterug.activities.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.webandrioz.scopeafterug.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.webandrioz.scopeafterug.activities.BaseActivity;
import com.webandrioz.scopeafterug.activities.SignUpActivity;
import com.webandrioz.scopeafterug.adapters.ChatAdapter;
import com.webandrioz.scopeafterug.models.Chat;
import com.webandrioz.scopeafterug.utils.SharedPreferenceProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ChatRoom  extends BaseActivity{

    private Button btn_send_msg;
    private EditText input_msg;
    private TextView chat_conversation;
    ArrayList<Chat> chatArray=new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    ChatAdapter chatAdapter;
    private String user_name,room_name;
    private DatabaseReference root ;
    private String temp_key;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        btn_send_msg = (Button) findViewById(R.id.btn_send);
        input_msg = (EditText) findViewById(R.id.msg_input);
//        chat_conversation = (TextView) findViewById(R.id.textView);
        listView= (ListView) findViewById(R.id.scrollView);

        user_name = new SharedPreferenceProvider().fatchDataLegislators(ChatRoom.this,"USER_NAME");
        room_name = getIntent().getExtras().get("room_name").toString();
        getSupportActionBar().setTitle(room_name);

//        setTitle(" Room - "+room_name);

        root = FirebaseDatabase.getInstance().getReference().child(room_name);

        btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,Object> map = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);

                DatabaseReference message_root = root.child(temp_key);
                Map<String,Object> map2 = new HashMap<String, Object>();
                map2.put("name",user_name);
                map2.put("msg",input_msg.getText().toString());

                message_root.updateChildren(map2);
                input_msg.setText("");
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private String chat_msg,chat_user_name;
//    ArrayList<String > chatArr=new ArrayList<>();

    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){

            chat_msg = (String) ((DataSnapshot)i.next()).getValue();
            chat_user_name = (String) ((DataSnapshot)i.next()).getValue();
            chatArray.add(new Chat(chat_user_name,chat_msg,"Email"));
//            chatArr.add(chat_user_name +" : "+chat_msg +" \n");
//            arrayAdapter = new ArrayAdapter<String>(this,R.layout.chat_listview_layout,chatArr);
            chatAdapter=new ChatAdapter(this,chatArray);
            listView.setAdapter(chatAdapter);
            listView.setSelection(listView.getAdapter().getCount()-1);
            chatAdapter.notifyDataSetChanged();
//            chat_conversation.append(chat_user_name +" : "+chat_msg +" \n");
        }


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