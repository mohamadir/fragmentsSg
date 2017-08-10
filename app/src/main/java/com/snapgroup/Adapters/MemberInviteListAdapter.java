package com.snapgroup.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.snapgroup.Classes.MemberInviteItem;
import com.snapgroup.R;

import java.util.ArrayList;

/**
 * Created by snapgroup2 on 10/08/17.
 */

public class MemberInviteListAdapter extends BaseAdapter {
    private ArrayList<MemberInviteItem> membersArray;

    private Activity context_1;
    public MemberInviteListAdapter(Activity context,ArrayList<MemberInviteItem> membersArray)
    {
        context_1= context;
        this.membersArray=membersArray;
    }
    @Override
    public int getCount()
    {
        return membersArray.size();

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
    public View getView(int i, View convertView, ViewGroup viewGroup) {



        MemberInviteListAdapter.ViewHolder viewHolder = null;
        // initilize the new view that will convert the simple view

        if (convertView == null) {
            convertView = LayoutInflater.from(context_1).inflate(
                    R.layout.member_choose_item_layout, null);
            viewHolder = new MemberInviteListAdapter.ViewHolder();


            convertView.setTag(viewHolder);
        } else {
            /**
             * Once the instance of the row item's control it will use from
             * already created controls which are stored in convertView as a
             * ViewHolder Instance
             * */
            viewHolder = (MemberInviteListAdapter.ViewHolder) convertView.getTag();
        }
        // set the text of all the views in the item to be the values from the newsArray
        Log.i("payy","im here");

        return convertView;


    }
    public class ViewHolder{
        public TextView name;
        public ImageView profileImage;
    }
}
