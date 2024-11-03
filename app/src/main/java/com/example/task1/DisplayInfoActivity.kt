package com.example.task1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DisplayInfoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_display_info)

        val userEmailTextView = findViewById<TextView>(R.id.userEmailTextView)
        val recipientEmailTextView = findViewById<TextView>(R.id.recipientEmailTextView)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)
        val clearButton = findViewById<Button>(R.id.clearButton)

        val userEmail = intent.getStringExtra("USER_EMAIL")
        val recipientEmail = intent.getStringExtra("RECIPIENT_EMAIL")
        val message = intent.getStringExtra("MESSAGE")

        userEmailTextView.text = "From: $userEmail"
        recipientEmailTextView.text = "To: $recipientEmail"
        messageTextView.text = "Message: $message"

        clearButton.setOnClickListener {

            userEmailTextView.text = "From:"
            recipientEmailTextView.text = "To:"
            messageTextView.text = "Message:"

            Toast.makeText(this, "cleared successfully", Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.display)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}