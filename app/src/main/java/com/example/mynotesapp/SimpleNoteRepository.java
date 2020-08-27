package com.example.mynotesapp;

import java.util.ArrayList;
import java.util.List;

// TODO Переделать на файлы / БД
public class SimpleNoteRepository implements NoteRepository {

    private List<Note> notes = new ArrayList<>();

    @Override
    public Note getNoteById(String id) {
        for (Note note: notes) {
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
        for (Note note: notes) {
            if (note.getId().equals(id)) {
                notes.remove(note);
            }
        }
    }
}
