package com.example.assesmentquestions

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity12 : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main12)

        dbHelper = DatabaseHelper(this)

        val question = "Lately, have you been feeling reasonably content and happy?"

        findViewById<Button>(R.id.button1).setOnClickListener {
            saveAnswer(question, "Yes, I am very content", 1)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            saveAnswer(question, "Overall, I am content but i have had bad days too", 2)
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            saveAnswer(question, "It has been tough but i push through ", 3)
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            saveAnswer(question, "I find it hard to be happy ", 4)
        }

        findViewById<Button>(R.id.finishButton).setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveAnswer(question: String, answer: String, score: Int) {
        dbHelper.saveResponse(question, answer, score)
        Toast.makeText(this, "Response saved: $answer", Toast.LENGTH_SHORT).show()
    }
}
