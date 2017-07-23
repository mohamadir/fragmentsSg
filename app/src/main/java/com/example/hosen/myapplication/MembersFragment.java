package com.example.hosen.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MembersFragment extends Fragment {
    ImageView imageButton;
    Intent intent;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ListView list;
    GridView androidGridView;
    // set the images on the gradview for the members
    Integer[] randImg={R.drawable.person1,R.drawable.person2,R.drawable.person3,R.drawable.person4,R.drawable.person5};
    Integer[] imageIDs=new Integer[31];

    //here what the kind of tthe memebrr
    String[] itemname ={
            "    Group Assistant",
            "    Group Assistant",
            "    Group Assistant",
    };
    String[] lastName ={
            "    Anglena",
            "   David",
            "    Yossi",
    };
    // the photos of the memebes assistant
    Integer[] imgid={
            R.drawable.person3,
            R.drawable.girl1,
            R.drawable.person1,
    };
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_members, container, false);
        androidGridView = (GridView) view.findViewById(R.id.gridt);// her i use gridview for pictures for members....
        androidGridView.setAdapter(new ImageAdapterGridView(getActivity()));

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) { // to  knnow which member i chose.
                Toast.makeText(getActivity(), "Grid Item " + (position + 1) + " Selected", Toast.LENGTH_LONG).show();
            }
        });
        CustomListAdapter adapter=new CustomListAdapter(getActivity(), itemname,lastName, imgid);
        list=(ListView)view.findViewById(R.id.list); // her i use list view for the gruop leader and grop assistant
        list.setAdapter(adapter);
        imageButton = (ImageView) view.findViewById(R.id.imageButton);
        imageButton.setClickable(true);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.fragment1,new UserTeamLeader()).commit();


                Fragment fr=new UserTeamLeader();
                FragmentManager fm=getFragmentManager();
                android.app.FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("CID222", "im vlaue from fragment");
                fr.setArguments(args);
                ft.replace(R.id.fragment1, fr);
                ft.commit();

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getActivity(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
        Random r = new Random();
        int Low = 0;
        int High = 4;

        for(int i=0;i<31;i++)
        {
            int Result = r.nextInt(High-Low) + Low;
            imageIDs[i]=randImg[Result];
        }
        return view;
    }


    public class ImageAdapterGridView extends BaseAdapter {
        /// class for the members photo i use gridview the extands from BaseAdapter
        private Context mContext;

        public ImageAdapterGridView(Context c) {
            mContext = c;
        }

        public int getCount() {
            return imageIDs.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView mImageView;

            if (convertView == null) {
                mImageView = new ImageView(mContext);
                mImageView.setLayoutParams(new GridView.LayoutParams(130, 130));
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


                mImageView.setPadding(5,5, 5, 5);
            } else {
                mImageView = (ImageView) convertView; // set the photo to the xml.
            }
            mImageView.setImageResource(imageIDs[position]); // set in which postion to put the photo
            return mImageView;
        }
    }


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

        public View getView(int position,View view,ViewGroup parent) {
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

}
