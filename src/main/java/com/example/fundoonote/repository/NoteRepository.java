package com.example.fundoonote.repository;

import com.example.fundoonote.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}