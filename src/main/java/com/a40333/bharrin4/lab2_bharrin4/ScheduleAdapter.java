package com.a40333.bharrin4.lab2_bharrin4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.a40333.bharrin4.lab2_bharrin4.R.layout.schedule_item;

/**
 * Created by User on 2/8/2017.
 */

class ScheduleAdapter extends ArrayAdapter<Team> {
    ScheduleAdapter (Context context, ArrayList<Team> teamInfo) {
        super(context, R.layout.schedule_item, teamInfo);
    }
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(schedule_item, parent, false);

        Team matchItem        = getItem(position);


        ImageView teamLogo = (ImageView) scheduleView.findViewById(R.id.teamPic);
        String mDrawableName = matchItem.getOpposingLogo();
        Log.d("logo", mDrawableName);
        int resID = getContext().getResources().getIdentifier(mDrawableName , "drawable",  getContext().getPackageName());
        teamLogo.setImageResource(resID);


        TextView teamName = (TextView) scheduleView.findViewById(R.id.teamNameText);
        teamName.setText(matchItem.getOpposingName());


        TextView date = (TextView) scheduleView.findViewById(R.id.dateText);
        date.setText(matchItem.getDate());

        return scheduleView;
    }
}
