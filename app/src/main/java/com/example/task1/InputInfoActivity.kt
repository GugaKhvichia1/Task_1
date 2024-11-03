package com.example.task1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InputInfoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input_info)

        val userEmailEditText = findViewById<EditText>(R.id.userEmailEditText)
        val recipientEmailEditText = findViewById<EditText>(R.id.recipientEmailEditText)
        val messageEditText = findViewById<EditText>(R.id.messageEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)

        sendButton.setOnClickListener {
            val userEmail = userEmailEditText.text.toString().trim()
            val recipientEmail = recipientEmailEditText.text.toString().trim()
            val message = messageEditText.text.toString().trim()

            if (userEmail.isEmpty() || recipientEmail.isEmpty() || message.isEmpty()) {
                Toast.makeText(this, "All fields must be filled out", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!userEmail.contains("@") || !recipientEmail.contains("@")) {
                Toast.makeText(this, "Both emails must contain '@'", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if ((userEmail.length + recipientEmail.length + message.length) > 250) {
                Toast.makeText(this, "Total input exceeds 250 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, DisplayInfoActivity::class.java).apply {
                putExtra("USER_EMAIL", userEmail)
                putExtra("RECIPIENT_EMAIL", recipientEmail)
                putExtra("MESSAGE", message)
            }
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.input)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}