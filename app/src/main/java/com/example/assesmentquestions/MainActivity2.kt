package com.example.assesmentquestions

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        dbHelper = DatabaseHelper(this)

        val question = "If you have been worried lately, have you experienced disturbed sleep because of it?"

        findViewById<Button>(R.id.button1).setOnClickListener {
            saveAnswer(question, "I feel well-rested", 1)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            saveAnswer(question, "I sleep the desired amount my body needs", 2)
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            saveAnswer(question, "I sleep less than usual", 3)
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            saveAnswer(question, "I do not sleep well", 4)
        }

        findViewById<Button>(R.id.finishButton).setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }

    private fun saveAnswer(question: String, answer: String, score: Int) {
        dbHelper.saveResponse(question, answer, score)
        Toast.makeText(this, "Response saved: $answer", Toast.LENGTH_SHORT).show()
    }
}
