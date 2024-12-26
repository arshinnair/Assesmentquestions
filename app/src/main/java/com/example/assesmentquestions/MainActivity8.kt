package com.example.assesmentquestions

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity8 : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        dbHelper = DatabaseHelper(this)

        val question = "Have you been able to deal with your problems?"

        findViewById<Button>(R.id.button1).setOnClickListener {
            saveAnswer(question, "Yes, with ease", 1)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            saveAnswer(question, "Yes, i am able to manage", 2)
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            saveAnswer(question, "i need help on most days", 3)
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            saveAnswer(question, "i am not able to deal with my problems", 4)
        }

        findViewById<Button>(R.id.finishButton).setOnClickListener {
            val intent = Intent(this, MainActivity9::class.java)
            startActivity(intent)
        }
    }

    private fun saveAnswer(question: String, answer: String, score: Int) {
        dbHelper.saveResponse(question, answer, score)
        Toast.makeText(this, "Response saved: $answer", Toast.LENGTH_SHORT).show()
    }
}
