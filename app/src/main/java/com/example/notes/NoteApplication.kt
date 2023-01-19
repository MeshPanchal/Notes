package com.example.notes

import android.app.Application
import com.example.notes.Database.NoteDatabase
import com.example.notes.Repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application() {
	private val applicationScope = CoroutineScope(SupervisorJob())

	private val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
	val repository by lazy { NoteRepository(database.getNoteDao()) }
}