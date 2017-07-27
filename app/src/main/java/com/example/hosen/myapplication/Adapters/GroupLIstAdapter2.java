package com.example.hosen.myapplication.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.hosen.myapplication.Models.GroupInList;
import com.example.hosen.myapplication.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by snapgroup2 on 27/07/17.
 */

public class GroupLIstAdapter2 extends BaseAdapter {
    private Activity context;
    private ArrayList<GroupInList> grouplist;

    public GroupLIstAdapter2(Activity context2, ArrayList<GroupInList> grouplist){
        this.context=context2;
        this.grouplist=grouplist;
    }
    @Override
    public int getCount() {
        return grouplist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup ViewGroup) {
        GroupLIstAdapter2.ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.group_item, null);
            viewHolder = new GroupLIstAdapter2.ViewHolder();
            viewHolder.destinationTv = (TextView) convertView.findViewById(R.id.destination);
            viewHolder.descriptionTv= (TextView) convertView.findViewById(R.id.description);
            viewHolder.startingAtTv = (TextView) convertView.findViewById(R.id.starting_at);
            viewHolder.durationTv = (TextView) convertView.findViewById(R.id.trip_duration);
            viewHolder.groupIv=(ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (GroupLIstAdapter2.ViewHolder) convertView.getTag();

        }

         /*   try {
                URL imageURl = new URL(grouplist.get(position).getImage());
                Log.i("imageUrl",imageURl.toString()+"");
                Bitmap groupImage = BitmapFactory.decodeStream(imageURl.openConnection().getInputStream());
                viewHolder.groupIv.setImageBitmap(groupImage);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/



        Picasso.with(context).load(grouplist.get(position).getImage()).into(viewHolder.groupIv);
        viewHolder.durationTv.setText("20");
            viewHolder.startingAtTv.setText(grouplist.get(position).getStart_date().toString());
            String desc = getNwords(grouplist.get(position).getDescritption().toString());
            viewHolder.descriptionTv.setText(desc);
            String origin = grouplist.get(position).getOrigin().toString();
            String dest = grouplist.get(position).getDestination().toString();
            viewHolder.destinationTv.setText(origin + " To " + dest);


        return convertView;

    }
    public class ViewHolder{
        public TextView destinationTv;
        public TextView descriptionTv;
        public TextView startingAtTv;
        public TextView durationTv;
        public ImageView groupIv;
    }
    public String getNwords(String str)
    {
        String [] arr = str.split(" ");
        int N=3;
        String nWords="";
        for(int i=0; i<4; i++){
            nWords = nWords + " " + arr[i] ;
        }
        return nWords;
    }


    public  Drawable drawable_from_url(String url, String src_name) throws
            java.net.MalformedURLException, java.io.IOException
    {
        return Drawable.createFromStream(((java.io.InputStream)
                new java.net.URL(url).getContent()), src_name);
    }
}
