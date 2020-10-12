package com.example.mynotesapp;

import android.annotation.SuppressLint;
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
import java.util.UUID;

public class NoteEditActivity extends AppCompatActivity {
    private FileNoteRepository fileNoteRepository;
    private EditText head;
    private EditText text;
    private EditText deadlineDate;
    private CheckBox checkBox;
    private ImageButton dateBtn;
    private String noteId;
    String createDateTime;
    String modifiedDateTime;

    public void setNoteRepository(FileNoteRepository fileNoteRepository) {
        this.fileNoteRepository = fileNoteRepository;
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
        inflater.inflate(R.menu.back, menu);
        return true;
    }


    private void init() {
        head = findViewById(R.id.head);
        text = findViewById(R.id.text);
        checkBox = findViewById(R.id.checkBox);
        dateBtn = findViewById(R.id.dateBtn);
        deadlineDate = findViewById(R.id.deadlineDate);
        Intent intent = getIntent();
        noteId = intent.getStringExtra("idExtr");
        head.setText(intent.getStringExtra("headExtr"));
        text.setText(intent.getStringExtra("textExtr"));
        deadlineDate.setText(intent.getStringExtra("deadlineDateExtr"));
    }

    private void listen() {
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    dateBtn.setVisibility(View.VISIBLE);
                    deadlineDate.setVisibility(View.VISIBLE);
                } else {
                    dateBtn.setVisibility(View.INVISIBLE);
                    deadlineDate.setVisibility(View.INVISIBLE);
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
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                deadlineDate.setText(dayOfMonth + "." + month + "." + year);
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
        String body = String.valueOf(text.getText());
        createDateTime = date.toString();
        modifiedDateTime = createDateTime;
        String id = UUID.randomUUID().toString();
        String modifiedDateTime = date.toString();
        String deadline = String.valueOf(deadlineDate.getText());
        Note note = new Note(name, body, createDateTime, modifiedDateTime, id, deadline);
        if (noteId != null) {
            fileNoteRepository.deleteById(noteId);
        }
        fileNoteRepository.saveNote(note);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                createNote();
                finish();
                return true;
            case R.id.back:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}