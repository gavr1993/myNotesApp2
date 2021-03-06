package com.example.mynotesapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NotesActivity extends AppCompatActivity {
    private ImageButton noteAddBtn;
    private FileNoteRepository fileNoteRepository;
    private NotesListAdapter adapter;

    public void setNoteRepository(FileNoteRepository fileNoteRepository) {
        this.fileNoteRepository = fileNoteRepository;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        final ListView notesList = findViewById(R.id.notesList);
        adapter = new NotesListAdapter(this, null);
        notesList.setAdapter(adapter);
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = adapter.getItem(position);
                Intent intent = new Intent(NotesActivity.this, NoteEditActivity.class);
                intent.putExtra("idExtr", note.getId());
                intent.putExtra("headExtr", note.getName());
                intent.putExtra("textExtr", note.getBody());
                intent.putExtra("deadlineDateExtr", note.getDeadline());
                startActivity(intent);
            }
        });
        notesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Note note = adapter.getItem(position);
                AlertDialog.Builder delDialog = new AlertDialog.Builder(NotesActivity.this);
                delDialog.setTitle(R.string.delNote);
                delDialog.setIcon(R.drawable.delete);
                delDialog.setMessage(R.string.delDialog);
                delDialog.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        fileNoteRepository.deleteById(String.valueOf(note.getId()));
                        adapter.delNote(note);
                        adapter.notifyDataSetChanged();
                    }
                });
                delDialog.setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                delDialog.show();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        final List<Note> notes = fileNoteRepository.getNotes();
        Collections.sort(notes, new Comparator<Note>() {
            @Override
            public int compare(Note note1, Note note2) {
                if (TextUtils.isEmpty(note1.getDeadline()) && !TextUtils.isEmpty(note2.getDeadline())) {
                    return 1;
                }

                if (!TextUtils.isEmpty(note1.getDeadline()) && TextUtils.isEmpty(note2.getDeadline())) {
                    return -1;
                }

                if (TextUtils.isEmpty(note1.getDeadline()) && TextUtils.isEmpty(note2.getDeadline())) {
                    return note1.getCreateDateTime().compareTo(note2.getCreateDateTime());
                }

                int compRes = 0;
                try {
                    compRes = note1.getDeadlineDate().compareTo(note2.getDeadlineDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (compRes == 0) {
                    return note1.getCreateDateTime().compareTo(note2.getCreateDateTime());
                }
                return compRes;
            }
        });
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
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