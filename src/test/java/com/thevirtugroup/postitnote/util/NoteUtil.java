package com.thevirtugroup.postitnote.util;

import com.thevirtugroup.postitnote.model.Note;

public enum NoteUtil {

    INSTANCE;

    public Note create() {
        Note note = new Note(1L,"","");
        return note;
    }
}
