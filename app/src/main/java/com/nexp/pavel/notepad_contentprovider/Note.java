package com.nexp.pavel.notepad_contentprovider;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;

public class Note {

    public static final String NOTE_ID = "id";

    public static final String NOTE_TITLE = "title";
    public static final String NOTE_DATE = "last_date";
    public static final String NOTE_TEXT = "text";

    long id;
    String title;

    String text;
    String lastDate;

    public Note( String title, String text, String lastDate) {
        this.title = title;
        this.text = text;
        this.lastDate = lastDate;
    }

    public Note(){

    }

    public static Note fromContentValues(ContentValues values) {

        final Note note = new Note(values.getAsString(NOTE_TITLE), values.getAsString(NOTE_TEXT), values.getAsString(NOTE_DATE));

        return note;
    }


}
