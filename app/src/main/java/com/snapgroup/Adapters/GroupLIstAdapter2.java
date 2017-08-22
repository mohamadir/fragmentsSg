package com.snapgroup.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
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
import com.snapgroup.Models.GroupInList;
import com.snapgroup.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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
        for(int i=0;i<grouplist.size();i++)
            Log.i("IddAdapter",grouplist.get(i).get_id()+","+grouplist.get(i).getTitle());

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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.newgroupitemdetails, null);
            viewHolder = new ViewHolder();
            viewHolder.titleTv = (TextView) convertView.findViewById(R.id.group_list_item_titleTv);
            viewHolder.roleIv = (ImageView) convertView.findViewById(R.id.group_role_iv);
            //viewHolder.descriptionTv= (TextView) convertView.findViewById(R.id.description);
          //  viewHolder.startingAtTv = (TextView) convertView.findViewById(R.id.starting_at);
           // viewHolder.durationTv = (TextView) convertView.findViewById(R.id.trip_duration);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.groupIv=(ImageView) convertView.findViewById(R.id.imgParis);
        viewHolder.groupLeaderIv=(ImageView)convertView.findViewById(R.id.groupLeaderIv) ;
        Picasso.with(context).load(grouplist.get(position).getImage()).into(viewHolder.groupIv);
        Picasso.with(context).load("http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg").transform(new CircleTransform()).into(viewHolder.groupLeaderIv);
        viewHolder.titleTv.setText(grouplist.get(position).getTitle()+"--"+grouplist.get(position).get_id());



        // viewHolder.durationTv.setText("20");
        //viewHolder.startingAtTv.setText(grouplist.get(position).getStart_date().toString());
        if(grouplist.get(position).getDescritption().length()>40) {
            String desc = getNwords(grouplist.get(position).getDescritption().toString());
            //viewHolder.descriptionTv.setText(desc);
        }

            //viewHolder.descriptionTv.setText(grouplist.get(position).getDescritption().toString());
        //String origin = grouplist.get(position).getOrigin().toString();
        //String dest = grouplist.get(position).getDestination().toString();
       // viewHolder.destinationTv.setText(origin + " To " + dest);


        return convertView;

    }
    public class ViewHolder{
        public TextView destinationTv;
        public TextView descriptionTv;
        public TextView titleTv;
       // public TextView startingAtTv;
        public TextView durationTv;
        public ImageView groupIv;
        public ImageView groupLeaderIv;
        public ImageView roleIv;
    }
    public String getNwords(String str)
    {
        String [] arr = str.split(" ");
        int N=3;
        String nWords="";
        for(int i=0; i<arr.length; i++){
            nWords = nWords + " " + arr[i] ;
        }
        return nWords;
    }

    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
    public  Drawable drawable_from_url(String url, String src_name) throws
            MalformedURLException, IOException
    {
        return Drawable.createFromStream(((java.io.InputStream)
                new URL(url).getContent()), src_name);
    }
}
