package com.a40333.bharrin4.lab2_bharrin4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String []> al = new ArrayList<String []>();
    DatabaseHelper dbHelper;
    int num = 0;
    ArrayList<Game> games = new ArrayList<Game>();
    ArrayList<Team> teams = new ArrayList<Team>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ND Athletics");

        dbHelper = new DatabaseHelper(getApplicationContext());
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 0, 1);


        MyCsvFileReader myCsvFileReader = new MyCsvFileReader(getApplicationContext());
        String mDrawableName = "schedule";
        int resID = getResources().getIdentifier(mDrawableName , "raw", getPackageName());
        al = myCsvFileReader.readCsvFile(resID);

        for (String [] temp : al) {
            String [] gameTemp = {temp[4], temp[0], temp[1], temp[6], temp[7], temp[12]};
            String [] teamTemp = {temp[4], temp[2], temp[3], temp[5], temp[8], temp[9], temp[10], temp[11], temp[12]};
            Game game = new Game(gameTemp);
            Team team = new Team(teamTemp);
            dbHelper.insertData(game, team);
            //everything is successfully inserted!
        }


        games = dbHelper.returnGames();
        teams = dbHelper.returnTeams();

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getApplicationContext(), teams);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(MainActivity.this, DetailActivity.class);
                mIntent.putExtra("team", teams.get(position));
                mIntent.putExtra("game", games.get(position));
                startActivity(mIntent);
            }
        };
        // this will automatically attach the listener to each item of the listview.
        scheduleListView.setOnItemClickListener (clickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public String gameSchedule() {
        StringBuilder str = new StringBuilder();

        for (Game s : dbHelper.returnGames())
        {
            str.append(s.getOpposingName());
            str.append("\t");
            str.append(s.getGameLocation());
            str.append("\t");
            str.append(s.getDate());
            str.append("\n");
        }

        String string = str.toString();
        return string;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {
            //code for sharing the schedule
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("plain/text");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BasketBall Matches");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, gameSchedule());
            startActivity(android.content.Intent.createChooser(shareIntent, "Share via"));
        }

        else if (res_id == R.id.sync) {
            // Snackbar with Try Again action
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);

            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Sync is not yet implemented", Snackbar.LENGTH_LONG);

            snackbar.setAction("Try Again", new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    Snackbar.make(coordinatorLayout, "Wait for the next few labs. Thank you for your patience", Snackbar.LENGTH_LONG).show();

                }

            });

            snackbar.show();

        }

        else if (res_id == R.id.settings) {
            // Floating Contextual Menu with options
            View view = (View) findViewById(R.id.scheduleListView);
            registerForContextMenu(view);
            openContextMenu(view);
            unregisterForContextMenu(view);

        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //code to inflate floating_contextual_menu
        getMenuInflater().inflate(R.menu.floating_contextual_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == R.id.women) {
            //to be implemented later
        }
        if (item_id == R.id.men) {
            //to be implemented later
        }
        if (item_id == R.id.on_campus) {
            //to be implemented later
        }
        if (item_id == R.id.off_campus) {
            //to be implemented later
        }
        return false;
    }


}
