package com.example.fundoonote.service;

import com.example.fundoonote.entity.Note;
import com.example.fundoonote.repository.NoteRepository;
import com.example.fundoonote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public Note create(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note update(Long id, Note note) {
        Note existing = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        existing.setTitle(note.getTitle());
        existing.setContent(note.getContent());

        return noteRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}