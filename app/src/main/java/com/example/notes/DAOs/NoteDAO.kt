package com.example.notes.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notes.Note

@Dao
interface NoteDAO {

	@Insert
	suspend fun insertNote(note: Note)

	@Delete
	suspend fun deleteNote(note: Note)

	@Query(value = "Select * from notes_table order by id asc")
	fun getAllNotes(): LiveData<List<Note>>
}