package com.example.timetableapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.timetableapp.Utils.LetterImageView;

import androidx.appcompat.widget.Toolbar;

public class DayDetail extends AppCompatActivity {
    private ListView listView;
    private Toolbar toolbar;

    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] Saturday;

    public static String[] Time;

    private String[] preferredDay;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);

        setupUIViews();
        initToolbar();
        setupListView();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupUIViews(){
        listView = (ListView)findViewById(R.id.lvDayDetail);
        toolbar = (Toolbar)findViewById(R.id.toolbarDaydetail);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(weeklyView.sharedPreferences.getString(weeklyView.SEL_DAY, null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupListView(){
        Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday = getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);
        Saturday = getResources().getStringArray(R.array.Saturday);

        Time = getResources().getStringArray(R.array.Time);

        String selectedDay = weeklyView.sharedPreferences.getString(weeklyView.SEL_DAY, null);

        if(selectedDay.equalsIgnoreCase("Monday")){
            preferredDay = Monday;
        } else if (selectedDay.equalsIgnoreCase("Tuesday")){
            preferredDay = Tuesday;
        }else if (selectedDay.equalsIgnoreCase("Wednesday")){
            preferredDay = Wednesday;
        }else if (selectedDay.equalsIgnoreCase("Thursday")){
            preferredDay = Thursday;
        }else if (selectedDay.equalsIgnoreCase("Friday")){
            preferredDay = Friday;
        }else if (selectedDay.equalsIgnoreCase("Saturday")){
            preferredDay = Saturday;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, preferredDay, Time);
        listView.setAdapter(simpleAdapter);
    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
//        private LayoutInflater layoutInflater = getLayoutInflater(); //check the layout inflater if something went wrong
        private LayoutInflater layoutInflater;
        private TextView subject, time;
        private String[] subjectArray;
        private String[] timeArray;
        private LetterImageView letterImageView;

        public SimpleAdapter(Context context, String[] subject, String[] time){
            mContext = context;
            subjectArray = subject;
            timeArray = time;
            layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = layoutInflater.inflate(R.layout.daydetailsingle, null);
            }

            subject = (TextView)convertView.findViewById(R.id.tvDayDetails);
            time = (TextView)convertView.findViewById(R.id.tvTimeDayDetail);
            letterImageView = (LetterImageView)convertView.findViewById(R.id.ivDayDetails);

            subject.setText(subjectArray[position]);
            time.setText((timeArray[position]));

            letterImageView.setOval(true);
            letterImageView.setLetter(subjectArray[position].charAt(0));

//            if(subjectArray[position].equalsIgnoreCase("Timetable")){
//                imageView.setImageResource(R.drawable.timetable);
//            } else if(titleArray[position].equalsIgnoreCase("Subjects")){
//                imageView.setImageResource(R.drawable.books);
//            }else if(titleArray[position].equalsIgnoreCase("Faculty")){
//                imageView.setImageResource(R.drawable.contact);
//            }else if(titleArray[position].equalsIgnoreCase("Resources")){
//                imageView.setImageResource(R.drawable.settings);
//            } else {
//                imageView.setImageResource(R.drawable.settings);
//            }
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
