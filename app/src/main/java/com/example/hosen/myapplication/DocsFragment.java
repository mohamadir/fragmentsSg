package com.example.hosen.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.hosen.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public DocsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_docs, container, false);

        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp); // her i use ExpandableListView  for the docs

        // preparing list data
        prepareListData();


        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long l) {
                // on click, check if the group list is expanded
                if(expListView.isGroupExpanded(groupPosition)){
                    // if the group list expanded, collapse it
                    expListView.collapseGroup(groupPosition);
                    Log.d("ExpdList", "Exit");
                } else {
                    // if the group list collapse, first collapse all the expanded group list if there any
                    int count = expListView.getCount();
                    Log.d("ExpdList", "Count: " + count);
                    for(int i = 0; i < count; i++){
                        if(i!=groupPosition)
                            expListView.collapseGroup(i);
                        else {
                            if(expListView.getChildAt(0)!=null)
                                expListView.expandGroup(i);
                        }
                    }
                    // then expand the this one
                    Log.d("ExpdList", "Open");
                }
                return true;
            }
        });
        expListView.setAdapter(listAdapter);
        return view;

    }


    private void prepareListData() {


        listDataHeader = new ArrayList<String>(); // the names of the header in the docs activity
        listDataChild = new HashMap<String, List<String>>(); // list data child is the view insidd the header

        // Adding header data
        listDataHeader.add("FLights Docs");
        listDataHeader.add("Hotel Docs");
        listDataHeader.add("Buus Docs");
        listDataHeader.add("Tour Guide Docs");
        listDataHeader.add("Buus Docs");

        // Adding child data
        List<String> uploads = new ArrayList<String>();
        uploads.add("Passport");
        uploads.add("Visa");
        uploads.add("Somthing else");

        listDataChild.put(listDataHeader.get(0), uploads); // Header, Child data
        listDataChild.put(listDataHeader.get(1), uploads); // Header, Child data
        listDataChild.put(listDataHeader.get(2), uploads); // Header, Child data
        listDataChild.put(listDataHeader.get(3), uploads); // Header, Child data
        listDataChild.put(listDataHeader.get(4), uploads); // Header, Child data

    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {   // ExpandableListAdapter exatnas from the BaseExpandableListAdapter to make the list view with the up and downn
        // and we Override the function what we want

        private Context _context;
        private List<String> _listDataHeader;
        private HashMap<String, List<String>> _listDataChild; // her we use hashmap to take the view for every list

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;

        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon); // return object of the child dara
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, // how ever list item work
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = (String) getChild(groupPosition, childPosition);
            // take gropupostion and childpostion and put the info about them into group postion and child postion

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item, null); // take the view of the childview from the xml and put them into the childdata;
            }

            TextView txtListChild = (TextView) convertView  // what i want to change ...
                    .findViewById(R.id.t1);

            TextView t2 = (TextView) convertView.findViewById((R.id.t2));
            txtListChild.setText(childText);
            if(txtListChild.getText().toString().equals("Passport")) {
                t2.setText("Airways policy");
            }
            if(txtListChild.getText().toString().equals("Visa")) {
                t2.setText("FLight POlicy");
            }
            if(txtListChild.getText().toString().equals("Somthing else")) {
                t2.setText("whatever");
            }

            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))  /// return the postion of the child
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) { // retrun the object of the group ( header)
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            // how the groupVIew works
            String headerTitle = (String) getGroup(groupPosition); // take the every list header;
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group, null); // take from the xml the list_group (Names of the heade)...
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.lblListHeader); // take the data and put them into the header
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
