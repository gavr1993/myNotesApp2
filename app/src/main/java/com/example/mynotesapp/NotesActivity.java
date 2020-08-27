package com.example.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class NotesActivity extends AppCompatActivity {
    private ImageButton noteAddBtn;
    private NoteRepository noteRepository;
    private NotesListAdapter adapter;

    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        final ListView notesList = findViewById(R.id.notesList);
        adapter = new NotesListAdapter(this, null);
        notesList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.setNotes(noteRepository.getNotes());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        init();
        fabListen();
        return true;
    }

    private void fabListen() {
        noteAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this, NoteEditActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        noteAddBtn = findViewById(R.id.noteAddBtn);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(NotesActivity.this, PrefActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
