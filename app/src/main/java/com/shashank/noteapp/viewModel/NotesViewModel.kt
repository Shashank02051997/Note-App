package com.shashank.noteapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shashank.noteapp.dao.NoteDao
import com.shashank.noteapp.database.NoteRoomDatabase
import com.shashank.noteapp.entity.Note

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val listNotes = MutableLiveData<ArrayList<Note>>()
    private var dao: NoteDao

    init {
        val database = NoteRoomDatabase.getDatabase(context)
        dao = database.getNoteDao()
    }

    fun setNotes() {
        val listItems = arrayListOf<Note>()

        listItems.addAll(dao.getAll())
        listNotes.postValue(listItems)
    }

    fun setNotesByLabel(label: String) {
        val listItems = arrayListOf<Note>()

        listItems.addAll(dao.getByLabel(label))
        listNotes.postValue(listItems)
    }

    fun setNotesByTitle(title: String) {
        val listItems = arrayListOf<Note>()

        listItems.addAll(dao.getByTitle(title))
        listNotes.postValue(listItems)
    }

    fun insertNote(note: Note) {
        dao.insert(note)
    }

    fun updateNote(note: Note) {
        dao.update(note)
    }

    fun deleteNote(note: Note) {
        dao.delete(note)
    }

    fun getNotes(): LiveData<ArrayList<Note>> {
        return listNotes
    }
}

