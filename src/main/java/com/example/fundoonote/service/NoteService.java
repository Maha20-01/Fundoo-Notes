package com.example.fundoonote.service;

import com.example.fundoonote.entity.Note;

import java.util.List;

public interface NoteService {

    Note create(Note note);

    List<Note> getAll();

    Note update(Long id, Note note);

    void delete(Long id);
}