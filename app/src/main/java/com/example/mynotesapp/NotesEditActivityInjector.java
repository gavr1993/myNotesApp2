package com.example.mynotesapp;

public class NotesEditActivityInjector implements Injector<NoteEditActivity> {

    private NoteRepository repository;

    public NotesEditActivityInjector(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void inject(NoteEditActivity target) {
        target.setNoteRepository(repository);
    }
}
