package com.example.noteapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.model.Note
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() , NoteAdapter.OnItemClikedListener {

    private lateinit var noteViewModel: NoteViewModel

    private lateinit var editTextTitle: TextInputEditText
    private lateinit var editTextContent: TextInputEditText

    private var binding : ActivityMainBinding ?= null

    private var noteId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val adapter = NoteAdapter()
        binding?.taskRV?.adapter = adapter
        binding?.taskRV?.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        noteViewModel.allNotes.observe(this) { notes ->
                notes?.let { adapter.setNotes(it ,this) }
            }

        binding?.addTaskFABtn?.setOnClickListener {
            newCreateTodo()
        }

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchNotes(newText)
                }
                return true
            }
        })

    }

    private fun newCreateTodo() {

        val dialogView = layoutInflater.inflate(R.layout.activity_add_edit_note, null)

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        editTextTitle= dialogView.findViewById(R.id.edit_text_title)
        editTextContent = dialogView.findViewById(R.id.edit_text_content)
        val dialogButton: Button = dialogView.findViewById(R.id.button_save)



        dialogButton.setOnClickListener {
            saveNewNotes(editTextTitle,editTextContent,dialogBuilder)
        }


        dialogBuilder.show()
    }

    private fun updateCreateTodo(note: Note) {
        val dialogView = layoutInflater.inflate(R.layout.activity_add_edit_note, null)

        val dialogBuilder = AlertDialog.Builder(this).setView(dialogView).create()
        editTextTitle= dialogView.findViewById(R.id.edit_text_title)
        editTextContent = dialogView.findViewById(R.id.edit_text_content)
        val dialogButton: Button = dialogView.findViewById(R.id.button_save)

        editTextTitle.setText(note.title)
        editTextContent.setText(note.content)



        dialogButton.setOnClickListener {
              saveUpdateNotes(editTextTitle,editTextContent,dialogBuilder,note)
        }


        dialogBuilder.show()

}

    private fun searchNotes(query: String) {
        val searchQuery = "%$query%"
        noteViewModel.searchNotes(searchQuery).observe(this) { notes ->
            (binding?.taskRV?.adapter as NoteAdapter).setNotes(notes ,this)
        }
    }

    private fun  saveNewNotes(addETTitle: TextInputEditText, addETContent: TextInputEditText, dialogBuilder: AlertDialog,){

        val title = addETTitle.text.toString()
        val content = addETContent.text.toString()

        if (title.isBlank() || content.isBlank()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val note = Note(
            id = noteId ?: 0,
            title = title,
            content = content
        )

        noteViewModel.insert(note)

        dialogBuilder.dismiss()

    }

    private fun saveUpdateNotes(addETTitle: TextInputEditText, addETContent: TextInputEditText, dialogBuilder: AlertDialog, note: Note) {

        val title = addETTitle.text.toString()
        val content = addETContent.text.toString()

        if (title.isBlank() || content.isBlank()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val note = Note(
            id = note.id,
            title = title,
            content = content
        )


       noteViewModel.update(note)

       dialogBuilder.dismiss()

    }

    override fun onItemClick(note: Note) {
        updateCreateTodo(note)
    }

    override fun deletetheList(note: Int) {
        noteViewModel.delete(note)
    }


}