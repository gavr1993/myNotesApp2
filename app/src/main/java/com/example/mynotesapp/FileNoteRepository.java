package com.example.mynotesapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileNoteRepository implements NoteRepository {
    private List<Note> notes = new ArrayList<>();
    private Context context;

    public FileNoteRepository(Context context) {
        this.context = context;
        File notesDir = new File(context.getFilesDir(), "notesDir");
        notesDir.mkdir();
        loadFromFiles(notesDir);
    }

    @Override
    public Note getNoteById(String id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    @Override
    public List<Note> getNotes() {
        return notes;
    }
    @Override
    public void saveNote(Note note) {
        notes.add(note);
    }

    @Override
    public void deleteById(String id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                notes.remove(note);
            }
        }
    }

    private void loadFromFiles(File notesDir) {
        final File[] files = notesDir.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (File file : files) {
            try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                final String name = bufferedReader.readLine();
                final String deadline = bufferedReader.readLine();
                final String body = bufferedReader.readLine();
                final String createdDate = bufferedReader.readLine();
                final String modifiedDate = bufferedReader.readLine();
                notes.add(new Note(name, body, createdDate, modifiedDate, file.getName(), deadline));
            } catch (IOException e) {
                // noop
            }
        }
    }

    void saveNotesToFile(List<Note> notes, File notesDir) {
        for (Note note : notes) {
            File notesFile = new File(notesDir, note.getId());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(note.getId(), true))) {
                writer.write(note.getName());
                writer.newLine();
                writer.write(note.getDeadline());
                writer.newLine();
                writer.write(note.getBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}