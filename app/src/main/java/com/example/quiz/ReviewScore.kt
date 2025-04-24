package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class ReviewScore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review_score)

        val ReviewScore_textView = findViewById<TextView>(R.id.ReviewScore_textView)
        val Restart_btn = findViewById<Button>(R.id.Restart_btn)
        val Exit_btn = findViewById<Button>(R.id.Exit_btn)

        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        val reviewText = StringBuilder()
        if (questions != null && answers != null && questions.size == answers.size) {
            for (i in questions.indices) {
                reviewText.append("${i + 1}. ${questions[i]} \n")
                reviewText.append("   Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            ReviewScore_textView.text = reviewText.toString()
        } else {
            ReviewScore_textView.text = "Failed to retrieve review data"
        }
        Restart_btn.setOnClickListener {
            startActivity(Intent(this, Quiz::class.java))
        }
        Exit_btn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}
