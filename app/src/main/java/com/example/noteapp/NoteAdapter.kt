package com.example.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notes = emptyList<Note>()
    private var onClikedListener : OnItemClikedListener ?= null

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTxt)
        val content: TextView = itemView.findViewById(R.id.descrTxt)
        val editImg: ImageView = itemView.findViewById(R.id.editImg)
        val deleteImg: ImageView = itemView.findViewById(R.id.deleteImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.title.text = currentNote.title
        holder.content.text = currentNote.content

        holder.editImg.setOnClickListener {
            onClikedListener?.onItemClick(currentNote)
        }

        holder.deleteImg.setOnClickListener {
            onClikedListener?.deletetheList(currentNote.id)
        }
    }

    override fun getItemCount() = notes.size

    internal fun setNotes(notes: List<Note> , itemClikedListener: OnItemClikedListener ) {
        this.notes = notes
        this.onClikedListener = itemClikedListener
        notifyDataSetChanged()
    }

     interface OnItemClikedListener {
        fun onItemClick(note: Note)
        fun deletetheList(note: Int)

    }
}
