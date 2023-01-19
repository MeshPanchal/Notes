package com.example.notes

import androidx.lifecycle.*
import com.example.notes.Repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

	val allNotes: LiveData<List<Note>> = repository.allNotes

	fun deleteNote(note: Note) = viewModelScope.launch {
		repository.delete(note)
	}

	fun insertNote(note: Note) = viewModelScope.launch {
		repository.insert(note)
	}
}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return NoteViewModel(repository) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}