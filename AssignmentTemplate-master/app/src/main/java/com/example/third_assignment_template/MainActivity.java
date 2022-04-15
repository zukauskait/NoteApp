package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView lvNotes;
    private MyAdapter listAdapter;
    private ArrayList<String> notesList = new ArrayList<>();
    private HashMap<String, String> notes = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lvNotes = findViewById(R.id.lvNotes);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        notes.clear();

        for (Map.Entry<String, ?> entry : sp.getAll().entrySet()) {

            notes.put(entry.getKey(),entry.getValue().toString());

        }

        for (HashMap.Entry<String, String> entry: notes.entrySet()) {


          this.notesList.add(entry.getKey());

        }

        this.listAdapter = new MyAdapter(notes);

        this.lvNotes.setAdapter(this.listAdapter);

        this.listAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //Your adapter loses reference to your list.
        //https://stackoverflow.com/questions/15422120/notifydatasetchange-not-working-from-custom-adapter
        notes.clear();

        for (Map.Entry<String, ?> entry : sp.getAll().entrySet()) {

            this.notes.put(entry.getKey(),entry.getValue().toString());

        }
        this.listAdapter = new MyAdapter(notes);

        this.lvNotes.setAdapter(this.listAdapter);

        this.listAdapter.notifyDataSetChanged();


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_note_activity:
                Intent addActivityIntent = new Intent(getApplicationContext(), com.example.third_assignment_template.AddNoteActivity.class);
                startActivity(addActivityIntent);



                return true;
            case R.id.delete_note_activity:
                Intent delActivityIntent = new Intent(getApplicationContext(), com.example.third_assignment_template.DeleteNoteActivity.class);
               startActivity(delActivityIntent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
