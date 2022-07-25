package com.thevirtugroup.postitnote.model;

public class Note extends Base {

    private Long id;
    private String name;
    private String text;

    public Note(Long id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Note() {
        new Note(1L, "", "");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
