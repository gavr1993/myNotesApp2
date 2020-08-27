package com.example.mynotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


class NotesListAdapter extends BaseAdapter {
    private List<Note> notes;
    private LayoutInflater inflater;
    NotesListAdapter(Context context, List<Note> notes) {
        if (notes == null) {
            this.notes = new ArrayList<>();
        } else {
            this.notes = notes;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    void addNote(Note note) {
        notes.add(note);
        notifyDataSetChanged();
    }

    void setNotes(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        if (position < notes.size()) {
            return notes.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.note, parent, false);
        }
        final Note note = notes.get(position);
        EditText head = view.findViewById(R.id.head);
        EditText body = view.findViewById(R.id.body);
        EditText deadline = view.findViewById(R.id.deadline);
        return view;
    }
}
