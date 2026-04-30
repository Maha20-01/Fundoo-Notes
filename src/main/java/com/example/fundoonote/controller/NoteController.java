package com.example.fundoonote.controller;

import com.example.fundoonote.entity.Note;
import com.example.fundoonote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public Note create(@RequestBody Note note) {
        return noteService.create(note);
    }

    @GetMapping
    public List<Note> getAll() {
        return noteService.getAll();
    }

    @PutMapping("/{id}")
    public Note update(@PathVariable Long id, @RequestBody Note note) {
        return noteService.update(id, note);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        noteService.delete(id);
        return "Deleted successfully";
    }
}