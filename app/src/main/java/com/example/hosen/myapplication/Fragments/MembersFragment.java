package com.example.hosen.myapplication.Fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hosen.myapplication.Adapters.CustomListAdapter;
import com.example.hosen.myapplication.Models.Member;
import com.example.hosen.myapplication.R;
import com.example.hosen.myapplication.Requests.MemberRequsts;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;


public class MembersFragment extends Fragment {
    ImageView imageButton;
    Intent intent;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView list;
    ArrayList<String> images_api;
    ArrayList<Member> members_info;
    GridView androidGridView;
    ArrayList<Integer> anArray;
    String[] first_names;
    String[] last_names;
    public static ArrayList <Member> members;

    // set the images on the gradview for the members

    //here what the kind of tthe memebrr
    String[] itemname ={
            "    GroupInList Assistant",
            "    GroupInList Assistant",
            "    GroupInList Assistant",
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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        list=(ListView)view.findViewById(R.id.list); // her i use list view for the gruop leader and grop assistant
        imageButton = (ImageView) view.findViewById(R.id.imageButton);
        members_info = new ArrayList<Member>();
        String urlGetMmebrs = "http://172.104.150.56/api/members";
        images_api = new ArrayList<String>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlGetMmebrs,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int z= 0;z < response.length();z++){
                                Log.i("hiHe" , "hi");
                                // Loop through the array elements
                                // Get current json object

                                JSONObject tees = response.getJSONObject(z);
                                JSONObject ProfileObejct = tees.getJSONObject("profile");
                                //finalMembers.add(new Member(ProfileObejct.getString(("first_name")),ProfileObejct.getString(("last_name")),ProfileObejct.getString(("profile_image"))));
                                images_api.add(ProfileObejct.getString(("profile_image")));
                                members_info.add(new Member(ProfileObejct.getString(("first_name")), ProfileObejct.getString(("last_name")), ProfileObejct.getString(("profile_image"))));


                            }
                            Log.i("size_img" , String.valueOf(images_api.size()));
                            Log.i("size_img" , String.valueOf(members_info.get(4).getFirst_name()) + " last name " + String.valueOf(members_info.get(4).getLast_name()));
                           // Log.i("member_info" , String.valueOf(members_info.size()));

                            androidGridView.setAdapter(new ImageAdapterGridView(getActivity()));
                            Log.i("problem5", " aaa");
                            anArray = new ArrayList<Integer>();
                            androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View v, int position, long id) { // to  knnow which member i chose.
                                    Toast.makeText(getActivity(), "Grid Item " + (position + 1) + " Selected", Toast.LENGTH_LONG).show();
                                    anArray.add(position,position);

                                }
                            });

                            CustomListAdapter adapter=new CustomListAdapter(getActivity(), itemname,lastName, imgid);

                            list.setAdapter(adapter);
                            imageButton.setClickable(true);
                            imageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    getFragmentManager().beginTransaction().replace(R.id.fragment1,new UserTeamLeaderFragment()).commit();

                                    Fragment fr=new UserTeamLeaderFragment();
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


                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Log.i("catch",e.getMessage().toString());
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.i("listener",error.getMessage().toString());
                        // Do something when error occurred
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
        Log.i("image_api" , String.valueOf(images_api.size()));

        return view;
    }

    public class ImageAdapterGridView extends BaseAdapter {
        /// class for the members photo i use gridview the extands from BaseAdapter
        private Context mContext;

        public ImageAdapterGridView(Context c) {
            mContext = c;
        }

        public int getCount() {
            return images_api.size();
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
                mImageView.setPadding(2,2,2,2);
            } else {
                mImageView = (ImageView) convertView; // set the photo to the xml.
            }
            Picasso.with(mContext).load(images_api.get(position)).into(mImageView);
            //mImageView.setImageResource(); // set in which postion to put the photo
            return mImageView;
        }
    }




}
