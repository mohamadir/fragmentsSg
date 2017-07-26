package com.example.hosen.myapplication.Activities;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hosen.myapplication.Fragments.DatePickeerFragment2;
import com.example.hosen.myapplication.Fragments.DatePickerFragment;
import com.example.hosen.myapplication.R;


public class NewGroupProfile2Activity extends AppCompatActivity {

    ListView list;
    String[] lastName ={
            "  Anglena basdhkjlk",
            "   David lasadasd"
    };
    // the photos of the memebes assistant
    Integer[] imgid={
            R.drawable.girl1,
            R.drawable.person3
    };

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_profile2);

        AssistantsAdapter adapter=new AssistantsAdapter(this, lastName, imgid);
        list=(ListView)findViewById(R.id.listAssistants); // her i use list view for the gruop leader and grop assistant
        list.setAdapter(adapter);

    }
    public void showDatePickerDialog2(View v) {
        DialogFragment newFragment = new DatePickeerFragment2();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public class AssistantsAdapter extends ArrayAdapter<String> {
        // class for Assistant group i use customlidtadapter  that extandsa from the adapter for the liostview
        //
        private final Activity context; // the activity/
        private final String[] itemname; // names of the Assistant group
        private final Integer[] imgid;// image for every one

        // constructor
        public AssistantsAdapter(Activity context, String[] itemname,Integer[] imgid) {
            super(context, R.layout.profilephoto, itemname);
            // TODO Auto-generated constructor stub

            this.context=context;
            this.itemname=itemname;
            this.imgid=imgid;
        }

        public View getView(int position,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.profilephoto, null,true); // set the id in the xml to be for every list vview

            TextView txtTitle = (TextView) rowView.findViewById(R.id.textView4);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView4);

            txtTitle.setText(itemname[position]);
            imageView.setImageResource(imgid[position]);
            return rowView; // return view for everyitem//

        };
    }
}
