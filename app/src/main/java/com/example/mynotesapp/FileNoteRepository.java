package com.example.mynotesapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileNoteRepository implements NoteRepository {

    private List<Note> notes = new ArrayList<>();

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

    void saveNotesToFile(List<Note> notes) {
        for (Note note : notes) {
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
