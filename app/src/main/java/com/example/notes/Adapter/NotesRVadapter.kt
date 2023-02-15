package com.example.notes

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// adapter used to adapt views with NoteRecyclerview

class NotesRVadapter(private val context: Context, private val listener: InotesRVadapter) :
	RecyclerView.Adapter<NotesRVadapter.NoteViewHolder>() {

	 private val allNotes = ArrayList<Note>()  //

	inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val     textView = itemView.findViewById<TextView>(R.id.note_text)
		val deletebtn = itemView.findViewById<ImageView>(R.id.deletebtn)

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
		val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
		return NoteViewHolder(view)
	}

	override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
		val currentNote = allNotes[position]
		holder.textView.text = currentNote.name_text

		holder.deletebtn.setOnClickListener {
			listener.onItemClicked(allNotes[holder.adapterPosition])
		}

	}

	override fun getItemCount(): Int {
		return allNotes.size
	}

	@SuppressLint("NotifyDataSetChanged")
	fun updateList(newList: List<Note>) {
		allNotes.clear()
		allNotes.addAll(newList)
		notifyDataSetChanged()
	}


}

interface InotesRVadapter {
	fun onItemClicked(note: Note)
}