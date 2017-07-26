package com.example.hosen.myapplication.Fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hosen.myapplication.Adapters.ChatListAdapter;
import com.example.hosen.myapplication.R;

public class ChatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // Params
    private String[] chat_array = {"1", "3", "1", "2", "1", "2", "2"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
    /*    Bundle arguments = getArguments();
        String desired_string = arguments.getString("aaa");
        Log.i("hihihi",desired_string);*/
        View view= inflater.inflate(R.layout.fragment_chat, container, false);
        // Create the Group List Adapter
        ChatListAdapter chatListAdapter = new ChatListAdapter(getActivity(), chat_array);
       /* if(getArguments().getString("CID222")!=null){
          String strtext = getArguments().getString("CID222");
            Log.i("CID222",strtext);
        }*/
        // Get list view

        ListView chatListView = (ListView) view.findViewById(R.id.chat_list);

        // Set the adapter to the list
        chatListView.setAdapter(chatListAdapter);
        return view;
    }
}
