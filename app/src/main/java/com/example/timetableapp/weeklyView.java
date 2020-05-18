package com.example.timetableapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.timetableapp.Utils.LetterImageView;

import androidx.appcompat.widget.Toolbar;

public class weeklyView extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_view);

        setupUIViews();
        initToolbar();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.toolbarWeek);
        listView = (ListView)findViewById(R.id.lvWeek);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Week");
    }
    private void setupListView(){
        String[] week = getResources().getStringArray(R.array.Week);
        WeekAdapter adapter = new WeekAdapter(this, R.layout.week_activity_singlt_item, week);
        listView.setAdapter(adapter);
    }

    public class WeekAdapter extends ArrayAdapter{
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] week = new String[]{};
        public WeekAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource=resource;
            this.week=objects;
            layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetter);
                holder.tvWeek = (TextView)convertView.findViewById(R.id.tvMain);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            return convertView;
        }
        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvWeek;
        }
    }
}
