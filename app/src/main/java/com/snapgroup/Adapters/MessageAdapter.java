package com.snapgroup.Adapters;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snapgroup.Models.Message;
import com.snapgroup.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> mMessages;
    private Context context_;
    public MessageAdapter(Context context, List<Message> messages) {
        mMessages = messages;
        context_=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SharedPreferences settings=context_.getSharedPreferences("Chat",MODE_PRIVATE);
        Boolean imSend = settings.getBoolean("Chat",false);
        int layout=-1;
        if(imSend==true)
            layout=R.layout.chat_item_left;
        else
            layout=R.layout.chat_item_right;

        SharedPreferences.Editor editor=context_.getSharedPreferences("Chat",MODE_PRIVATE).edit();
        editor.putBoolean("send",false);
        editor.commit();
        switch (viewType) {
            /*case Message.TYPE_MESSAGE:
                layout = R.layout.chat_item_right;
                break;*/

//            case Message.TYPE_LOG:
//                layout = R.layout.item_log;
//                break;
            case Message.TYPE_ACTION:
                layout = R.layout.chat_item_typing;
                break;
        }
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Message message = mMessages.get(position);
        viewHolder.setMessage(message.getMessage());
        viewHolder.setUsername(message.getUsername());
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getType();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mUsernameView;
        private TextView mMessageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mUsernameView = (TextView) itemView.findViewById(R.id.username);
            mMessageView = (TextView) itemView.findViewById(R.id.message);
        }

        public void setUsername(String username) {
            if (null == mUsernameView) return;
            mUsernameView.setText(username);
        }

        public void setMessage(String message) {
            if (null == mMessageView) return;
            mMessageView.setText(message);
        }
    }
}
