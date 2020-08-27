package com.example.mynotesapp;

public class NotesActivityInjector implements Injector<NotesActivity> {

    private NoteRepository repository;

    public NotesActivityInjector(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void inject(NotesActivity target) {
        target.setNoteRepository(repository);
    }
}
