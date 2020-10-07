package com.example.mynotesapp;

public class NotesEditActivityInjector implements Injector<NoteEditActivity> {

    private FileNoteRepository repository;

    public NotesEditActivityInjector(FileNoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void inject(NoteEditActivity target) {
        target.setNoteRepository(repository);
    }
}
