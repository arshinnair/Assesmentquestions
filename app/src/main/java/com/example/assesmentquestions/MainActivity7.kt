package com.example.assesmentquestions

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity7 : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        dbHelper = DatabaseHelper(this)

        val question = "Have you been able to enjoy your daily tasks/activities"

        findViewById<Button>(R.id.button1).setOnClickListener {
            saveAnswer(question, "Yes, i am happy doing my tasks", 1)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            saveAnswer(question, "i am able to get through my tasks without any hiccups", 2)
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            saveAnswer(question, "Not too much", 3)
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            saveAnswer(question, "They feel like a burden to me", 4)
        }

        findViewById<Button>(R.id.finishButton).setOnClickListener {
            val intent = Intent(this, MainActivity8::class.java)
            startActivity(intent)
        }
    }

    private fun saveAnswer(question: String, answer: String, score: Int) {
        dbHelper.saveResponse(question, answer, score)
        Toast.makeText(this, "Response saved: $answer", Toast.LENGTH_SHORT).show()
    }
}
