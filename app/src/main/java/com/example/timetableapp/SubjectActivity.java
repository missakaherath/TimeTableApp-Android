package com.example.timetableapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.timetableapp.Utils.LetterImageView;

import androidx.appcompat.widget.Toolbar;

public class SubjectActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private String[] subjects;
    private String[] syllabuses;
    public static SharedPreferences subjectPreference;
    public static String SEL_SUB;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        setupUIViews();
        initToolbar();
        setupListView();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.toolbarSubject);
        listView = (ListView)findViewById(R.id.lvSubject);
        subjectPreference = getSharedPreferences(SEL_SUB, MODE_PRIVATE);
    }
    @SuppressLint("RestrictedApi")
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subjects");
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void setupListView(){
        subjects = getResources().getStringArray(R.array.Subjects);
//        syllabuses = getResources().getStringArray(R.array.Syllabus);

        SubjectAdapter subjectadapter = new SubjectAdapter(this, R.layout.subject_single_item, subjects);
        listView.setAdapter(subjectadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0: {
                        startActivity(new Intent(SubjectActivity.this, SubjectDetails.class));
                        subjectPreference.edit().putString(SEL_SUB, "OOP").apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(SubjectActivity.this, SubjectDetails.class));
                        subjectPreference.edit().putString(SEL_SUB,"OOSD").apply();
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(SubjectActivity.this, SubjectDetails.class));
                        subjectPreference.edit().putString(SEL_SUB,"OS");
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });

    }
    public class SubjectAdapter extends ArrayAdapter {
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] subjects;
        public SubjectAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource=resource;
            this.subjects=objects;
            layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivSubjectLogo = (LetterImageView)convertView.findViewById(R.id.ivSubjectLetter);
                holder.tvSubject = (TextView)convertView.findViewById(R.id.tvSubject);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.ivSubjectLogo.setOval(true);
            holder.ivSubjectLogo.setLetter(subjects[position].charAt(0));
            holder.tvSubject.setText(subjects[position]);

            return convertView;
        }
        class ViewHolder{
            private LetterImageView ivSubjectLogo;
            private TextView tvSubject;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
