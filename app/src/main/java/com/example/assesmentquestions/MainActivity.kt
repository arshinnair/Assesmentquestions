package com.example.assesmentquestions

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        val question = "How well have you been able to focus on your daily tasks?"

        findViewById<Button>(R.id.button1).setOnClickListener {
            saveAnswer(question, "Very Well", 1)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            saveAnswer(question, "Moderately Well", 2)
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            saveAnswer(question, "Not at all", 3)
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            saveAnswer(question, "I feel overwhelmed", 4)
        }

        findViewById<Button>(R.id.finishButton).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    private fun saveAnswer(question: String, answer: String, score: Int) {
        dbHelper.saveResponse(question, answer, score)
        Toast.makeText(this, "Response saved: $answer", Toast.LENGTH_SHORT).show()
    }
}
