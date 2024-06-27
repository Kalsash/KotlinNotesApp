package com.example.notesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginRight

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val container = findViewById<LinearLayout>(R.id.notesContainer)

        // Apply edge-to-edge to the main container
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // AddButton
        val addButton = findViewById<Button>(R.id.button)
        addButton.setOnClickListener {
            val noteText = findViewById<EditText>(R.id.InputText).text.toString()
            if (noteText.isNotBlank()) {
                addNoteToContainer(noteText, container)
            }
        }
    }

    private fun addNoteToContainer(noteText: String, container: LinearLayout) {
        val noteView = LinearLayout(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        noteView.layoutParams = params
        noteView.orientation = LinearLayout.HORIZONTAL

        val textView = TextView(this)
        textView.textSize = 30f
        textView.text = noteText + "  "
        val button = Button(this, null, R.style.CustomButtonStyle)
        button.setTextAppearance(R.style.CustomButtonStyle)
        button.text = "Удалить"

        button.setOnClickListener {
            container.removeView(noteView)
        }

        noteView.addView(textView)
        noteView.addView(button)
        container.addView(noteView)
    }
}