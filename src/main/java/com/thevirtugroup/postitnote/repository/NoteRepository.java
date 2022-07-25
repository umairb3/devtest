package com.thevirtugroup.postitnote.repository;

import com.thevirtugroup.postitnote.exception.ResourceNotFoundException;
import com.thevirtugroup.postitnote.model.Note;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class NoteRepository {

    private List<Note> notes;

    public NoteRepository() {
        notes = new ArrayList<>();
        notes.add(new Note(1L, "Note1", "note1 text"));
    }

    public Note save(Note request) {
        request.setId(maxId() + 1);
        request.setCreatedAt(LocalDateTime.now());
        notes.add(request);
        return request;
    }

    public Note findById(Long id) {
        return notes.stream()
                .filter(note -> id == note.getId())
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Note not found, Id: %d", id)));
    }

    public Note update(Note request) {
        notes.stream()
                .filter(note -> note.getId() == request.getId())
                .findFirst()
                .ifPresent(o -> {
                    o.setName(request.getName());
                    o.setText(request.getText());
                });

        return request;

    }

    public List<Note> findAll() {
        return notes;
    }

    public void delete(Long id) {
        notes.removeIf(note -> id == note.getId());
    }


    private Long maxId() {
        if (notes.isEmpty()) return 1L;

        return notes.stream()
                .max(Comparator.comparingLong(Note::getId))
                .orElseThrow(NoSuchElementException::new).getId();
    }
}
