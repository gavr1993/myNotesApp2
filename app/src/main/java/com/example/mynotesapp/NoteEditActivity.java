package com.example.mynotesapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class NoteEditActivity extends AppCompatActivity {
    private NoteRepository noteRepository;
    private EditText head;
    private EditText body;
    private EditText deadline;
    private CheckBox checkBox;
    private ImageButton dateBtn;
    int counter = 1;
    String createDateTime;
    String modifiedDateTime;

    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

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

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePick();
            }
        });

    }

    private void datePick() {
        Calendar todayCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener onDateSet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                onDateSet,
                todayCalendar.get(Calendar.YEAR),
                todayCalendar.get(Calendar.MONTH),
                todayCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
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
        noteRepository.saveNote(note);
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