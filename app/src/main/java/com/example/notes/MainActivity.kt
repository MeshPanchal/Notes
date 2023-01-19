package com.example.notes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), InotesRVadapter {

	// view model objetct to access entire database ,no initialization
	//	viewModel = noteViewmodel.
	private val viewModel: NoteViewModel by viewModels {
		NoteViewModelFactory((application as NoteApplication).repository)
	}

	// Main activity views
	private lateinit var recyclerView: RecyclerView
	private lateinit var submitBtn: Button
	private lateinit var inputText: EditText

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.activity_main)

		recyclerView = findViewById(R.id.itemRV)
		submitBtn = findViewById(R.id.Submitbtn)
		inputText = findViewById(R.id.inputText)

		// set recyclerview with adapter , create adpater and set with rcv
		recyclerView.layoutManager = LinearLayoutManager(this)
		val adapter = NotesRVadapter(this, this)
		recyclerView.adapter = adapter

		// allnotes of viewmodel is livedata so when database get modified it observe database and can do desirable code here
		viewModel.allNotes.observe(this, Observer { list ->
			list?.let {
				adapter.updateList(it) // update method will update all notes
			}
		})

		// to save note submit button is used and this on click listener called when user taps submit button
		submitBtn.setOnClickListener {
			val noteText = inputText.text?.toString()
			noteText?.let {
				viewModel.insertNote(Note(noteText))
				// Toast is working
				Toast.makeText(this, "$noteText inserted ", Toast.LENGTH_SHORT).show()
			}
		}
	}

	// To delete note this is onclicked function used
	// user click delete view on interface and this method will execute and Note will be delete. it is the method of InotesRvadpater in NotesRvadapter
	// class
	override fun onItemClicked(note: Note) {
		viewModel.deleteNote(note)
		Toast.makeText(this, "${note.name_text} deleted ", Toast.LENGTH_SHORT).show()
	}

}