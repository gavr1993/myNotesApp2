package com.example.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.List;

public class NoteEditActivity extends AppCompatActivity {
    private List<Note> notes;
    private EditText head;
    private EditText body;
    private EditText deadline;
    private CheckBox checkBox;
    private ImageButton dateBtn;
    int counter = 1;
    String createDateTime;
    String modifiedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        init();
        listen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return true;
    }

    private void init() {
        head = findViewById(R.id.head);
        body = findViewById(R.id.body);
        checkBox = findViewById(R.id.checkBox);
        dateBtn = findViewById(R.id.dateBtn);
        deadline = findViewById(R.id.deadline);
    }

    private void listen() {
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    dateBtn.setVisibility(View.VISIBLE);
                    deadline.setVisibility(View.VISIBLE);
                } else {
                    dateBtn.setVisibility(View.INVISIBLE);
                    deadline.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void createNote() {
        String name = String.valueOf(head.getText());
        Date date = new Date();
        String text = String.valueOf(body.getText());

        createDateTime = date.toString();
        modifiedDateTime = createDateTime;
        String id = counter + "";
        counter++;
        String modifiedDateTime = date.toString();
        String deadlineDate = String.valueOf(deadline.getText());
        Note note = new Note(name, createDateTime, text, modifiedDateTime, id, deadlineDate);
        NotesListAdapter.addNote(note);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            createNote();
            Intent intent = new Intent(NoteEditActivity.this, NotesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}