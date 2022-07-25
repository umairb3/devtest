package com.thevirtugroup.postitnote.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import com.thevirtugroup.postitnote.service.NoteService;
import com.thevirtugroup.postitnote.util.NoteUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class NoteControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private NoteController noteController;

    private NoteRepository noteRepository = new NoteRepository();

    private NoteService noteService = new NoteService();

    private static final NoteUtil NOTE_UTIL = NoteUtil.INSTANCE;

    @Before
    public void init() {
        noteService.setNoteRepository(noteRepository);
        noteController = new NoteController();
        noteController.setNoteService(noteService);
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    public void test_save_with_success() throws Exception {
        Note note = NOTE_UTIL.create();
        String userModelJson = objectMapper.writeValueAsString(note);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/note")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(userModelJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(HttpStatus.OK.value(), status);
        assertNotNull(content);
    }

    @Test
    public void test_update_with_success() throws Exception {
        Note note = NOTE_UTIL.create();
        String userModelJson = objectMapper.writeValueAsString(note);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put(String.format("/v1/note/%d", 1L))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(userModelJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(HttpStatus.OK.value(), status);
        assertNotNull(content);
    }

    @Test
    public void test_findById_with_success() throws Exception {
        Note note = NOTE_UTIL.create();
        String userModelJson = objectMapper.writeValueAsString(note);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/v1/note/%d", 1L))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(userModelJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(HttpStatus.OK.value(), status);
        assertNotNull(content);
    }

    @Test
    public void test_findAll_with_success() throws Exception {
        Note note = NOTE_UTIL.create();
        String userModelJson = objectMapper.writeValueAsString(note);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/note")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(userModelJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(HttpStatus.OK.value(), status);
        assertNotNull(content);
    }
}
