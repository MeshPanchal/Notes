package com.example.notes.Repository

import androidx.lifecycle.LiveData
import com.example.notes.DAOs.NoteDAO
import com.example.notes.Note

class NoteRepository(private val noteDao: NoteDAO) {

	val allNotes: LiveData<List<Note>> = noteDao.getAllNotes() // livedata to observe database each moment

	// this insert and delete function are suspend functions because insert and delete methods of noteDao is suspend
	suspend fun insert(note: Note) {
		 noteDao.insertNote(note)
	}

	suspend fun delete(note: Note) {
		noteDao.deleteNote(note)
	}
}