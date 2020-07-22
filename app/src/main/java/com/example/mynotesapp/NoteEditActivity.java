package com.example.mynotesapp;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteEditActivity extends AppCompatActivity {
    private EditText head;
    private EditText body;
    private EditText deadline;
    private CheckBox checkBox;
    private List<Note> notes = new ArrayList<>();
    int counter = 1;
    String createDateTime;
    String modifiedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        init();
        createNote();
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
        notes.add(note);
    }

    private void init() {
        head = findViewById(R.id.head);
        body = findViewById(R.id.body);
        deadline = findViewById(R.id.deadline);
        checkBox = findViewById(R.id.checkBox);
    }
}