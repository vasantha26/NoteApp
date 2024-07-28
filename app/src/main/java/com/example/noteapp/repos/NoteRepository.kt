package com.example.noteapp.repos

import androidx.lifecycle.LiveData
import com.example.noteapp.dao.NoteDao
import com.example.noteapp.model.Note

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Int) {
        noteDao.delete(note)
    }

    fun searchNotes(query: String): LiveData<List<Note>> {
        return noteDao.searchNotes(query)
    }
}
