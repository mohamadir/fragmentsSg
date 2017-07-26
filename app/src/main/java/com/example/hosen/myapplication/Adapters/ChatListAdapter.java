package com.example.hosen.myapplication.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.hosen.myapplication.R;


public class ChatListAdapter extends ArrayAdapter<String> {

    // Params
    private final Activity context;
    private final String[] chat_array;

    public ChatListAdapter(Activity context, String[] chat_array) {
        super(context, R.layout.chat_item_left, chat_array);
        this.context = context;
        this.chat_array = chat_array;
    }


    // View override
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        // get the view layout
        View chatItemLeft = inflater.inflate(R.layout.chat_item_left, null, true);
        View chatItemRight = inflater.inflate(R.layout.chat_item_right, null, true);

        // Change the chat item direction left or right based on the correct user
        if(chat_array[position] == "1"){
            // set the layout to left
            return chatItemLeft;
        } else {
            // set the layout to right
            return chatItemRight;
        }

    }
}
