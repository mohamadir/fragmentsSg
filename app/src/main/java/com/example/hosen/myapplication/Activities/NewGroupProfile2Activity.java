package com.example.hosen.myapplication.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hosen.myapplication.Fragments.DatePickeerFragment2;
import com.example.hosen.myapplication.Fragments.DatePickerFragment;
import com.example.hosen.myapplication.R;

import org.w3c.dom.Text;

import java.util.Map;


public class NewGroupProfile2Activity extends AppCompatActivity {

    ListView list;
    Spinner maxMembersSp;
    TextView btNext,dateFromTv,dateToTv;
    String[] lastName ={
            "  Anglena basdhkjlk",
            "   David lasadasd"
    };
    // the photos of the memebes assistant
    Integer[] imgid={
            R.drawable.girl1,
            R.drawable.person3
    };
    int maxMembers;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_profile2);
        //printSharedPreferences();
        AssistantsAdapter adapter=new AssistantsAdapter(this, lastName, imgid);
        list=(ListView)findViewById(R.id.listAssistants); // her i use list view for the gruop leader and grop assistant
        dateFromTv=(TextView)findViewById(R.id.profile2Activity_dateFromTv);
        dateToTv=(TextView)findViewById(R.id.profile2Activity_dateToTv);
        maxMembersSp=(Spinner) findViewById(R.id.profile2Activity_maxMembersSp);
        maxMembersSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                maxMembers= Integer.parseInt(maxMembersSp.getSelectedItem().toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        list.setAdapter(adapter);
        btNext=(TextView)findViewById(R.id.profile2Activity_nextBt);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(NewGroupProfile2Activity.this
                ,NewGroupServicesActivity.class);
                updateSharedPreferences();
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

    }

    private void updateSharedPreferences() {
        SharedPreferences.Editor editor=getSharedPreferences("NewGroup",MODE_PRIVATE).edit();
        String dateFrom=dateFromTv.getText().toString();
        String dateTo=dateToTv.getText().toString();
        if(!dateFrom.equals(""))
            editor.putString("start_date",dateFrom);
        else
            editor.putString("start_date","");
        if(!dateTo.equals(""))
            editor.putString("end_date",dateTo);
        else
            editor.putString("end_date","");
        editor.putInt("max_members",maxMembers);
        editor.commit();
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

    }
    private void printSharedPreferences() {
        SharedPreferences NewGroupSp=this.getSharedPreferences("NewGroup",MODE_PRIVATE);
        Map<String, ?> allEntries = NewGroupSp.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.i("MAPP", entry.getKey() + ": " + entry.getValue().toString());
        }
    }
}
