package com.thevirtugroup.postitnote.rest;

import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 */
@RestController
@RequestMapping("/v1/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Note save(@RequestBody Note note) {
        return noteService.save(note);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Note update(@PathVariable("id") Long id, @RequestBody Note note) {
        note.setId(id);
        return noteService.update(note);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Note findById(@PathVariable("id") Long id) {
        return noteService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Note> findAll() {
        return noteService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        noteService.delete(id);
    }

    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }
}
