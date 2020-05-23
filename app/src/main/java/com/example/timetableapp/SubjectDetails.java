package com.example.timetableapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
public class SubjectDetails extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;

    public static String[] oop;
    public static String[] oosd;
    public static String[] os;

    public static String[] syllabus;

    private String selectedSub;

    private String[] selectedSubject;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        setupUIViews();
        initToolbar();
        setupListView();
    }
    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.toolbarSubjectDetail);
        listView = (ListView)findViewById(R.id.lvSubjectDetail);
    }
    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupListView(){
//        oop = getResources().getStringArray(R.array.OOPSyllabus);
//        oosd = getResources().getStringArray(R.array.OOSDSyllabus);
//        os = getResources().getStringArray(R.array.OSSyllabus);

        String[] titles = getResources().getStringArray(R.array.title);

        selectedSub = SubjectActivity.subjectPreference.getString(SubjectActivity.SEL_SUB, null);

        if(selectedSub.equalsIgnoreCase("OOP")){
            selectedSubject = oop;
            syllabus = getResources().getStringArray(R.array.OOPSyllabus);
        } else if (selectedSub.equalsIgnoreCase("OOSD")){
            selectedSubject = oosd;
            syllabus = getResources().getStringArray(R.array.OOSDSyllabus);
        } else if (selectedSub.equalsIgnoreCase("OS")){
            selectedSubject = os;
            syllabus = getResources().getStringArray(R.array.OSSyllabus);
        }

        SubjectAdapter subjectAdapter = new SubjectAdapter(this, titles, syllabus);
        listView.setAdapter(subjectAdapter);
    }
    public class SubjectAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater = getLayoutInflater();;
        private TextView title, syllabus;
        private String[] titleArray;
        private String[] syllabusArray;

//        LayoutInflater layoutInflater = getLayoutInflater();

        public SubjectAdapter(Context context, String[] titles, String[] syllabus){
            mContext = context;
            titleArray = titles;
            syllabusArray = syllabus;
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = layoutInflater.inflate(R.layout.subject_details_single_item, null);
            }

            title = (TextView)convertView.findViewById(R.id.tvSubjectDetailTitle);
            syllabus = (TextView)convertView.findViewById(R.id.tvSyllabus);

            title.setText(titleArray[position]);
            syllabus.setText((syllabusArray[position]));

            return convertView;
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
