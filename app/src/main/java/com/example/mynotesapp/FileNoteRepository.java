package com.example.mynotesapp;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileNoteRepository implements NoteRepository {
    private List<Note> notes = new ArrayList<>();
    private File notesDir;

    public FileNoteRepository(Context context) {
        notesDir = new File(context.getFilesDir(), "notesDir");
        //noinspection ResultOfMethodCallIgnored
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
        return new ArrayList<>(notes);
    }

    @Override
    public void saveNote(Note note) {
        notes.add(note);
        saveNoteToFile(note);
    }

    @Override
    public void deleteById(String id) {
        final Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            final Note note = iterator.next();
            if (note.getId().equals(id)) {
                iterator.remove();
                //noinspection ResultOfMethodCallIgnored
                new File(notesDir, note.getId()).delete();
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
                JSONObject json = new JSONObject(bufferedReader.readLine());
                notes.add(new Note(
                        json.optString("name"),
                        json.optString("body"),
                        json.optString("createdDate"),
                        json.optString("modifiedDate"),
                        file.getName(),
                        json.optString("deadline")));
            } catch (IOException | JSONException e) {
                // noop
            }
        }
    }

    void saveNoteToFile(Note note) {
        File notesFile = new File(notesDir, note.getId());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(notesFile, true))) {
            JSONObject json = new JSONObject();
            json.put("name", note.getName());
            json.put("body", note.getBody());
            json.put("createdDate", note.getCreateDateTime());
            json.put("modifiedDate", note.getModifiedDateTime());
            json.put("deadline", note.getDeadline());
            writer.write(json.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}