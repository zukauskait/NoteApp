package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }


    public void onAddNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);
        EditText txtName = findViewById(R.id.txtName);



        //https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        LinkedHashMap<String, String> newNotes = new LinkedHashMap<String, String>();

        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();

        //Inserts new note

        if(txtName.length()==0){
            Toast toast = Toast.makeText(context, "Name of the note cannot be empty.", duration);
            toast.show();
        }
        else if (txtNote.length()==0){
            Toast toast = Toast.makeText(context, "The note cannot be empty.", duration);
            toast.show();
        }
        else{

            newNotes.put(txtName.getText().toString(),txtNote.getText().toString());
            finish();
        }




        //Gets old notes
        for (Map.Entry<String, ?> entry : sp.getAll().entrySet()) {

           newNotes.put(entry.getKey().toString(),entry.getValue().toString());

        }

        spEd.clear();


        for (HashMap.Entry<String, String> entry: newNotes.entrySet()) {


           spEd.putString(entry.getKey(),entry.getValue().toString());


        }



        newNotes.clear();
        //spEd.clear();

        spEd.apply();



    }
}
