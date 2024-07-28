package com.example.noteapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class AddEditNoteActivity : AppCompatActivity() {

//    private lateinit var noteViewModel: NoteViewModel
//    private lateinit var editTextTitle: TextInputEditText
//    private lateinit var editTextContent: TextInputEditText
    private var noteId: Int? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_edit_note)
//
//        editTextTitle = findViewById(R.id.edit_text_title)
//        editTextContent = findViewById(R.id.edit_text_content)
//        val buttonSave = findViewById<Button>(R.id.button_save)
//
//        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
//
//        noteId = intent.getIntExtra("noteId", -1)
//        if (noteId != -1) {
//            noteViewModel.allNotes.observe(this) { notes ->
//                notes.find { it.id == noteId }?.let { note ->
//                    editTextTitle.setText(note.title)
//                    editTextContent.setText(note.content)
//                }
//            }
//        }
//
//        buttonSave.setOnClickListener {
//            val title = editTextTitle.text.toString()
//            val content = editTextContent.text.toString()
//
//            if (title.isBlank() || content.isBlank()) {
//                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            val note = Note(
//                id = noteId ?: 0,
//                title = title,
//                content = content
//            )
//
//            if (noteId == null) {
//                noteViewModel.insert(note)
//            } else {
//                noteViewModel.update(note)
//            }
//            finish()
//        }
//    }
}
