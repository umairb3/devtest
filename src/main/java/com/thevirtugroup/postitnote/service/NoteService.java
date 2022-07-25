package com.thevirtugroup.postitnote.service;

import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;
    public Note save(Note request) {
        return noteRepository.save(request);
    }

    public Note update(Note request) {
        return noteRepository.update(request);
    }

    public Note findById(Long id) {
        return noteRepository.findById(id);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public void delete(Long id) {
        noteRepository.delete(id);
    }

    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
}
