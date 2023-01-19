package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// this is Note class . user create note by using this class
// note contains two field id and text
@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "name_text") var name_text: String) {

	@PrimaryKey(autoGenerate = true)
	var id: Int = 0
}
