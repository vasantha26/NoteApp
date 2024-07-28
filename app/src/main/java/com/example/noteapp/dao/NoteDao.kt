package com.example.noteapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun delete(noteId: Int)

    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE title LIKE :searchQuery OR content LIKE :searchQuery")
    fun searchNotes(searchQuery: String): LiveData<List<Note>>
}
