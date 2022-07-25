package com.thevirtugroup.postitnote.service;

import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import com.thevirtugroup.postitnote.util.NoteUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class NoteServiceTest {

    private NoteService noteService;

    private NoteRepository noteRepository = new NoteRepository();

    @Before
    public void init() {
        noteService = new NoteService();
        noteService.setNoteRepository(noteRepository);
    }

    private static final NoteUtil NOTE_UTIL = NoteUtil.INSTANCE;

    @Test
    public void test_save_with_success() {
        Note response = noteService.save(NOTE_UTIL.create());
        assertNotNull(response);
    }

    @Test
    public void test_findById_with_success() {
        Note response = noteService.findById(1L);
        assertNotNull(response);
    }

    @Test
    public void test_findAll_with_success() {
        List<Note> notes = new ArrayList<>();
        notes.add(NOTE_UTIL.create());
        List<Note> records = noteService.findAll();
        assertNotNull(records);
    }
}
