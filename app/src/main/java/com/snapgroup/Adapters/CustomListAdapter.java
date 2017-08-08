package com.snapgroup.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.snapgroup.R;


/**
 * Created by root on 27/07/17.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    // class for Assistant group i use customlidtadapter  that extandsa from the adapter for the liostview
    //
    private final Activity context; // the activity/
    private final String[] itemname; // names of the Assistant group
    private final Integer[] imgid;// image for every one
    private final String[] lastName; // last name


    // constructor
    public CustomListAdapter(Activity context, String[] itemname, String[] lastName,Integer[] imgid) {
        super(context, R.layout.listview_activity, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.lastName=lastName;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_activity, null,true); // set the id in the xml to be for every list vview

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1); // the data that i set///

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText(lastName[position]);
        return rowView; // return view for everyitem//

    };
}