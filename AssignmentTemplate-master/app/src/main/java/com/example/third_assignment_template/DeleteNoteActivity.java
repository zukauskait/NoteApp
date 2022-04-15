package com.example.third_assignment_template;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    private String choice = "";
    private Spinner spinner;
    private ArrayList<String> notesList = new ArrayList<>();
    ArrayAdapter spinnerArrayAdapter;
    SharedPreferences sp;
    SharedPreferences.Editor spEd;
    LinkedHashMap<String, String> newNotes = new LinkedHashMap<String, String>();
    LinkedHashMap<String, String> cleanNotes = new LinkedHashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);
         sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         spEd = sp.edit();



        for (Map.Entry<String, ?> entry : sp.getAll().entrySet()) {
            notesList.add(entry.getValue().toString());
        }


        spinner = (Spinner) findViewById(R.id.note_picker);
        spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                notesList);
        spinner.setAdapter(spinnerArrayAdapter);





    }

    public void onDeleteNoteClick(View view) {



        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();


        if(notesList.isEmpty()) {

            Toast toast = Toast.makeText(context, "No notes left.", duration);
            toast.show();
        }
        else{
            choice = spinner.getSelectedItem().toString();
            notesList.remove(choice);
            Toast toast = Toast.makeText(context, "Note with the name "+choice+" was deleted.", duration);
            toast.show();
            spinner.setAdapter(spinnerArrayAdapter);
        }


        for (Map.Entry<String, ?> entry : sp.getAll().entrySet()) {

            if(!entry.getValue().toString().equals(choice)) {
              cleanNotes.put(entry.getKey(),entry.getValue().toString());
            }

        }

        spEd.clear();



        for (HashMap.Entry<String, String> entry: cleanNotes.entrySet()) {


            spEd.putString(entry.getKey(), entry.getValue().toString());
        }

        spEd.clear();
        spEd.apply();


        finish();



    }


}
