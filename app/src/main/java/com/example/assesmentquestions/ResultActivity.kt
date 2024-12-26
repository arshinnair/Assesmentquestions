package com.example.assesmentquestions

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last_page)

        dbHelper = DatabaseHelper(this)

        // List of questions
        val questions = listOf(
            "How well have you been able to focus on your daily tasks?",
            "If you have been worried lately, have you experienced disturbed sleep because of it?",
            "In your everyday activities/tasks, have you felt like you are playing a useful role?",
            "Have you ever felt like you were making capable decisions?",
            "How often do you feel stressed or tensed?",
            "Have you ever felt like you could not deal with your difficulties?",
            "Have you been able to enjoy your daily tasks/activities?",
            "Have you been able to deal with your problems?",
            "Have you been feeling sad & depressed?",
            "Have you ever felt like your confidence has reduced?",
            "Are you getting thoughts that you are worthless?",
            "Lately, have you been feeling reasonably content and happy?"
        )

        // Calculate the total score across all questions
        val totalScore = questions.sumOf { question ->
            dbHelper.calculateQuestionTotalScore(question)
        }

        // Display total score
        findViewById<TextView>(R.id.resultTextView).text = "Your Total Score: $totalScore /120"

        // Determine category based on total score
        val category = when {
            totalScore <= 30 -> "No significant mental health concerns (Healthy)"
            totalScore in 31..60 -> "Mild mental health concerns"
            totalScore in 61..90 -> "Moderate mental health concerns"
            else -> "Major mental health concerns. It is advisable to seek professional help."
        }

        // Display category
        findViewById<TextView>(R.id.TextView).text = category
    }
}
