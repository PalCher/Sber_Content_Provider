package com.nexp.pavel.notepad_contentprovider;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static final String NOTE_ID = "id";

    public static final String NOTE_TITLE = "title";
    public static final String NOTE_DATE = "last_date";
    public static final String NOTE_TEXT = "text";

    public static List<Note> convertCursorToNotes(Cursor cursor){
        List<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()){
            Note note = new Note();
            note.id = cursor.getInt(cursor.getColumnIndex(NOTE_ID));
            note.title = cursor.getString(cursor.getColumnIndex(NOTE_TITLE));
            note.text = cursor.getString(cursor.getColumnIndex(NOTE_TEXT));
            note.lastDate = cursor.getString(cursor.getColumnIndex(NOTE_DATE));
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    public static Note convertCursorToNote(Cursor cursor){
        Note note = new Note();
        while (cursor.moveToNext()){
            note.id = cursor.getInt(cursor.getColumnIndex(NOTE_ID));
            note.title = cursor.getString(cursor.getColumnIndex(NOTE_TITLE));
            note.text = cursor.getString(cursor.getColumnIndex(NOTE_TEXT));
            note.lastDate = cursor.getString(cursor.getColumnIndex(NOTE_DATE));

        }
        cursor.close();
        return note;
    }

    public static ContentValues converNoteToValues(Note note){
        ContentValues values = new ContentValues();
        values.put(NOTE_TITLE, note.title);
        values.put(NOTE_TEXT, note.text);
        values.put(NOTE_DATE, note.lastDate);
        return values;

    }
}
