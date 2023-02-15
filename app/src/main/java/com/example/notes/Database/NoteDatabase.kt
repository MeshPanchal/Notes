package com.example.notes.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notes.DAOs.NoteDAO
import com.example.notes.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

	abstract fun getNoteDao(): NoteDAO

	private class NoteDatabaseCallback(
		private val scope: CoroutineScope,
	) : RoomDatabase.Callback() {

		override fun onCreate(db: SupportSQLiteDatabase) {
			super.onCreate(db)
			INSTANCE?.let {
				scope.launch {

					// Delete all content here.

				}
			}
		}
	}


	companion object {

		@Volatile
		private var INSTANCE: NoteDatabase? = null

		fun getDatabase(
			context: Context,
			scope: CoroutineScope,
		): NoteDatabase {

			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					NoteDatabase::class.java,
					"Note_database"
				).build()
				INSTANCE = instance
				// return instance
				instance
			}
		}
	}

}

