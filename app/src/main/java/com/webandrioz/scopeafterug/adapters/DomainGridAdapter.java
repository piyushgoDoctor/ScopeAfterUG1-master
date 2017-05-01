package com.webandrioz.scopeafterug.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webandrioz.scopeafterug.activities.DomainActivity;
import com.webandrioz.scopeafterug.activities.ExamsActivity;
import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.models.Domains;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 26/2/17.
 */

public class DomainGridAdapter  extends BaseAdapter {


    Context con;
    ArrayList<Domains> domain=new ArrayList<>();

    public DomainGridAdapter(DomainActivity domainActivity, ArrayList<Domains> domain) {
        con=domainActivity;
        this.domain=domain;

    }




    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return domain.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    public class Holder
    {
        TextView tv;
        ImageView img;
//        CardView cd;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater li= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =li.inflate(R.layout.domian_grid_layout,null);
        Holder holder=new Holder();

//       holder.cd= (CardView) convertView.findViewById(R.id.card_viewHome);
//       convertView.findViewById(R.id.card_viewHome).setBackgroundColor(Color.parseColor("#ffffff"));
        holder.img= (ImageView) convertView.findViewById(R.id.domainGridViewImage);
        //holder.img.setVisibility(View.INVISIBLE);
        holder.tv= (TextView) convertView.findViewById(R.id.domainGridText);
        Log.e(TAG, "getView: "+"http://scopeafterug.xyz/domain/"+(position+1)+".jpg" );
        Picasso.with(con).load("http://scopeafterug.xyz/domain/"+(position+1)+".jpg").resize(100, 100).into(holder.img);

//        holder.img.setImageResource(images[position]);
        holder.tv.setText(domain.get(position).getDomainName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("EXAM ID"+position, "onClick: "+domain.get(position).getDomaainId() );
             Intent in=new Intent(con, ExamsActivity.class);
                in.putExtra("id",domain.get(position).getDomaainId());
                con.startActivity(in);
            }
        });



        return convertView;

    }
}
