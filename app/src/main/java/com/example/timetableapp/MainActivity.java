package com.example.timetableapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listview;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();
        initToolbar();
        setupListView();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.toolbarMain);
        listview = (ListView)findViewById(R.id.lvMain);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Timetable App");
    }
    private void setupListView(){
        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listview.setAdapter(simpleAdapter);
    }
    public class SimpleAdapter extends BaseAdapter{

        private Context mContext;
        private LayoutInflater layoutInflater = getLayoutInflater();;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

//        LayoutInflater layoutInflater = getLayoutInflater();

        public SimpleAdapter(Context context, String[] title, String[] description){
            mContext = context;
            titleArray = title;
            descriptionArray = description;
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
                convertView = layoutInflater.inflate(R.layout.main_activity_single_item, null);
            }

            title = (TextView)convertView.findViewById(R.id.tvMain);
            description = (TextView)convertView.findViewById(R.id.tvDescription);
            imageView = (ImageView)convertView.findViewById(R.id.ivMain);

            title.setText(titleArray[position]);
            description.setText((descriptionArray[position]));

            if(titleArray[position].equalsIgnoreCase("Timetable")){
                imageView.setImageResource(R.drawable.timetable);
            } else if(titleArray[position].equalsIgnoreCase("Subjects")){
                imageView.setImageResource(R.drawable.books);
            }else if(titleArray[position].equalsIgnoreCase("Faculty")){
                imageView.setImageResource(R.drawable.contact);
            }else if(titleArray[position].equalsIgnoreCase("Resources")){
                imageView.setImageResource(R.drawable.settings);
            } else {
                imageView.setImageResource(R.drawable.settings);
            }
            return convertView;
        }
    }
}
