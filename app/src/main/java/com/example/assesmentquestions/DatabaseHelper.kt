package com.example.assesmentquestions

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Assessment.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_RESPONSES = "responses"
        const val COLUMN_ID = "id"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_ANSWER = "answer"
        const val COLUMN_SCORE = "score"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_RESPONSES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_QUESTION TEXT,
                $COLUMN_ANSWER TEXT,
                $COLUMN_SCORE INTEGER
            )
        """
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_RESPONSES")
        onCreate(db)
    }

    fun saveResponse(question: String, answer: String, score: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_QUESTION, question)
            put(COLUMN_ANSWER, answer)
            put(COLUMN_SCORE, score)
        }
        db.insert(TABLE_RESPONSES, null, values)
        db.close()
    }

    fun calculateQuestionTotalScore(question: String): Int {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT SUM($COLUMN_SCORE) FROM $TABLE_RESPONSES WHERE $COLUMN_QUESTION = ?",
            arrayOf(question)
        )
        var totalScore = 0
        if (cursor.moveToFirst()) {
            totalScore = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return totalScore
    }
}
