package com.example.mynotesapp;

public class NotesActivityInjector implements Injector<NotesActivity> {

    private FileNoteRepository repository;

    public NotesActivityInjector(FileNoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void inject(NotesActivity target) {
        target.setNoteRepository(repository);
    }
}
